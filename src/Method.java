import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Mehtod - Methode
 *
 * Status: Möglicherweise komplett
 */
public class Method implements TypeInterface{

    // Return-Type
    Type returnType;

    // Name der Methode
    java.lang.String name;

    // Parameter der Metode
    Parameter params;

    // Statements innerhalb der Methode, sozusagen der "Code"
    Stmt stmt;

    // Liste mit allen lokalen Variablen der Methode
    // Der Index einer Variable in dieser Liste ist ebenfalls der interne
    // Index der Variable in der JVM, die benötigt wird, um auf die
    // richtige Variable bzw. den richtigen Parameter zuzugreifen
    List<Field> localVars;

    // Speichere den Anfang und das Ende der Methode in einem Label
    // Wird benötigt, um bei der Definition von lokalen Variablen den
    // Scope zu definieren.
    Label startLabel;
    Label endLabel;

    public Method(Type returnType, java.lang.String name, Parameter para, Stmt stmt) {
        this.returnType = returnType;
        this.name = name;
        this.params = para;
        this.stmt = stmt;
    }

    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return returnType;
    }
    
    /**
     * Code einer Methode generieren
     * @param cl This-Objekt der Klasse, in welcher die Methode enthalten ist
     * @param cw ClassWriter
     */
    public void codeGen(Class cl, ClassWriter cw) {
        System.out.printf("[Method] visitMethod(): %s, returning %s\n", name, returnType.name);
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, name, this.getTypeDescriptor(), null, null);

        localVars = new ArrayList<>();
        // Die erste Variable innerhalb einer Methode ist immer "this" und hat
        // offensichtlicht den selben Typ die die umschließende Klasse
        localVars.add(new Field(cl.type, "this"));

        startLabel = new Label();
        endLabel = new Label();
        System.out.println("[Method] visitLabel(startLabel)");
        mv.visitLabel(startLabel);

        for (Field f : params.params) {
            // Vor den lokalen Variablen kommen Parameter
            // Die benötigen für den Zugriff auch einen Index
            localVars.add(f);
            System.out.println("[Method] visitParameter(): " + f.name);
            mv.visitParameter(f.name, Opcodes.ACC_FINAL);
        }

        System.out.println("[Method] visitCode()");
        mv.visitCode();
        stmt.codeGen(cl, this, mv);

        // Eine Methode mit Return-Type void muss nicht zwingend mit einem return
        // abgeschlossen werden. Vergisst man das Return im Code, würde unser Programm
        // ohne die folgenden Zeilen allerdings abschmieren.
        if (returnType.name.equals("void")) {
            System.out.println("[Method] visitInsn(RETURN)");
            mv.visitInsn(Opcodes.RETURN);
        }

        System.out.println("[Method] visitLabel(endLabel)");
        mv.visitLabel(endLabel);

        System.out.println("[Method] visitMaxs()");
        mv.visitMaxs(0,0);

        System.out.println("[Method] visitEnd()");
        mv.visitEnd();
    }

    /**
     * Hole die lokale Variable mit dem entsprechenden Namen
     *
     * Es wird in der Liste gesucht, welche sich im Method-Objekt befindet.
     *
     * @param name Name der lokalen Variable
     * @return Lokale Variable als Field-Objekt, ansonsten null.
     *         Null dürfte eigentlich nicht auftreten.
     */
    public Field findLocalVarByName(String name) {
        return findLocalVarByName(name, this.localVars);
    }
    /**
     * Hole die lokale Variable mit dem entsprechenden Namen
     *
     * Es wird in der Liste gesucht, welche der Methode übergeben wird.
     *
     * @param name Name der lokalen Variable
     * @param localVarList Liste mit lokalen Variablen
     * @return Lokale Variable als Field-Objekt, ansonsten null.
     *         Null dürfte eigentlich nicht auftreten.
     */
    public static Field findLocalVarByName(String name, List<Field> localVarList) {
        for (Field f : localVarList) {
            if (f.name.equals(name))
                return f;
        }
        return null;
    }

    /**
     * Typ-Descriptor der Methode erstellen und zurückliefern
     *
     * Siehe auch: https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.3
     *
     * @return Descriptor der Methode
     */
    public String getTypeDescriptor() {
        String descriptor = "(";
        for (Field param : params.params) {
            descriptor += param.type.getTypeDescriptor();
        }
        descriptor += ")" + returnType.getTypeDescriptor();

        return descriptor;
    }
}

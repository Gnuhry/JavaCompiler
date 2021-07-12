import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Mehtod - Methode
 *
 * Status: Möglicherweise komplett
 */
public class Method implements TypeInterface{

    // Return-Type
    Type retty;

    // Name der Methode
    java.lang.String name;

    // Parameter der Metode
    Parameter para;

    // Statements innerhalb der Methode, sozusagen der "Code"
    Stmt stmt;

    // Liste mit allen lokalen Variablen der Methode
    // Der Index einer Variable in dieser Liste ist ebenfalls der interne
    // Index der Variable in der JVM, die benötigt wird, um auf die
    // richtige Variable bzw. den richtigen Parameter zuzugreifen
    List<Field> localVars;

    // TODO Liste mit Variablen und deren Index

    public Method(Type retty, java.lang.String name, Parameter para, Stmt stmt) {
        this.retty = retty;
        this.name = name;
        this.para = para;
        this.stmt = stmt;
    }

    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return retty;
    }
    
    /**
     * Code einer Methode generieren
     * @param cl This-Objekt der Klasse, in welcher die Methode enthalten ist
     * @param cw ClassWriter
     */
    public void codeGen(Class cl, ClassWriter cw) {
        System.out.printf("[Method] Visiting: %s, returning %s\n", name, retty.name);
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, name, this.getTypeDescriptor(), null, null);

        localVars = new ArrayList<>();
        // Die erste Variable innerhalb einer Methode ist immer "this" und hat
        // obviously den selben Typ die die umschließende Klasse
        localVars.add(new Field(cl.ty, "this"));

        for (Field f : para.params) {
            System.out.println("[Method] Visition method parameter: " + f.name);

            // Vor den lokalen Variablen kommen Parameter
            // Die benötigen für den Zugriff auch einen Index
            localVars.add(f);
            mv.visitParameter(f.name, Opcodes.ACC_PUBLIC);
        }

        mv.visitCode();
        System.out.println("[Method] Class name: " + stmt.getClass().getName());
        stmt.codeGen(cl, this, mv);
        mv.visitMaxs(0,0);
        mv.visitEnd();
    }

    public Field findLocalVarByName(String name) {
        for (Field f : localVars) {
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
        for (Field param : para.params) {
            descriptor += param.ty.getTypeDescriptor();
        }
        descriptor += ")" + retty.getTypeDescriptor();

        return descriptor;
    }
}

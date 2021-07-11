import java.util.Map;
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

    // Map mit allen lokalen Variablen der Methode
    Map<String, Type> localVars;

    // TODO Liste mit Variablen und deren Index

    public Method(Type retty, java.lang.String name, Parameter para, Stmt stmt) {
        this.retty = retty;
        this.name = name;
        this.para = para;
        this.stmt = stmt;
    }

    @Override
    public Type typeCheck(Map<String, Type> localVars, Class thisClass) {
        return retty;
    }
    
    /**
     * Code einer Methode generieren
     * @param cl This-Objekt der Klasse, in welcher die Methode enthalten ist
     * @param cw ClassWriter
     */
    public void codeGen(Class cl, ClassWriter cw) {
        System.out.printf("Visiting: %s, returning %s\n", name, retty.name);
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, name, this.getTypeDescriptor(), null, null);

        mv.visitCode();
        stmt.codeGen(cl, mv);
        mv.visitMaxs(0,0);
        mv.visitEnd();
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

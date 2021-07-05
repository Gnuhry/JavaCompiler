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

    // TODO Liste mit Variablen und deren Index

    public Method(Type retty, java.lang.String name, Parameter para, Stmt stmt) {
        this.retty = retty;
        this.name = name;
        this.para = para;
        this.stmt = stmt;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return retty;
    }
    
    /**
     * Code einer Methode generieren
     * @param cl This-Objekt der Klasse, in welcher die Methode enthalten ist
     * @param cw ClassWriter
     */
    public void codeGen(Class cl, ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, name, retty.typ, null, null);

        mv.visitCode();
        stmt.codeGen(mv);
        mv.visitMaxs(0,0);
        mv.visitEnd();
    }
}

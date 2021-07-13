import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;
import java.util.Vector;

/**
 * MethodCall - Methodenaufruf
 * <p>
 * Status: Unsicher
 * <p>
 * Bei einem Methodenaufruf werden die Opcodes INVOKESPECIAL und INVOKEVIRTUAL
 * benötigt. Soweit ich es verstanden hab, wird in unserem Fall in der Regel
 * INVOKESPECIAL verwendet. INVOKEVIRTUAL wird bei uns nur beim Konstruktor
 * verwendet.
 */
public class MethodCall extends StmtExpr {

    // Objekt, von welchem die Methode aufgerufen wird
    Expr expr;

    // Name der Methode
    String methodName;

    // Parameter der Methode
    Vector<Expr> params;

    /**
     * Methode eines bestimmten Objektes aufrufen
     *
     * @param expr  Das jeweilige Objekt (object.method(...))
     * @param methodName    Name der Methode
     * @param params Parameter der Methode
     */
    public MethodCall(Expr expr, String methodName, Vector<Expr> params) {
        this.expr = expr;
        this.methodName = methodName;
        this.params = params;
    }

    /**
     * Methode des eigenen Objektes (this) aufrufen
     *
     * @param methodName    Name der Methode
     * @param params Parameter der Methode
     */
    public MethodCall(String methodName, Vector<Expr> params) {
        this.expr = new This();
        this.methodName = methodName;
        this.params = params;
    }

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {

        // Kontruktor benötigt Opcode INVOKESPECIAL, ansonsten INVOKEVIRTUAL
        int opcode = methodName.equals("<init>") ? Opcodes.INVOKESPECIAL : Opcodes.INVOKEVIRTUAL;

        // Diese Implementierung hat einen sehr großen Nachteil:
        // Es lassen sich nur Methoden der eigenen Klasse aufrufen
        // TODO: Methoden anderer Klassen aufrufen?
        System.out.println("[MethodCall] " + methodName);
        String descriptor = cl.findMethodByName(methodName).getTypeDescriptor();

        // Der Owner ist der Name der Klasse, in welcher die Methode definiert ist
        mv.visitMethodInsn(opcode, cl.type.name, methodName, descriptor, false);
    }

    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        //TODO unsicher ob das so stimmt!
        return expr.typeCheck(localVars, thisClass);
    }
}

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;
import java.util.Vector;

/**
 * MethodCall - Methodenaufruf
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

        expr.codeGen(cl, meth, mv);

	for(Expr param : params) {
	    param.codeGen(cl, meth, mv);
	}

        // Diese Implementierung hat einen sehr großen Nachteil:
        // Es lassen sich vermutlich nur Methoden der eigenen Klasse aufrufen
        // TODO: Methoden anderer Klassen aufrufen?
        String descriptor = cl.findMethodByName(methodName).getTypeDescriptor();

        // Der Owner ist der Name der Klasse, in welcher die Methode definiert ist
        System.out.println("[MethodCall] visitMethodInsn(INVOKEVIRTUAL): " + methodName);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, cl.type.name, methodName, descriptor, false);
    }

    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return thisClass.findMethodByName(this.methodName).returnType;
    }
}

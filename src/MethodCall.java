import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
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
    String st;

    // Parameter der Methode
    Vector<Expr> exprs;

    /**
     * Methode eines bestimmten Objektes aufrufen
     *
     * @param expr  Das jeweilige Objekt (object.method(...))
     * @param st    Name der Methode
     * @param exprs Parameter der Methode
     */
    public MethodCall(Expr expr, String st, Vector<Expr> exprs) {
        this.expr = expr;
        this.st = st;
        this.exprs = exprs;
    }

    /**
     * Methode des eigenen Objektes (this) aufrufen
     *
     * @param st    Name der Methode
     * @param exprs Parameter der Methode
     */
    public MethodCall(String st, Vector<Expr> exprs) {
        this.expr = new This();
        this.st = st;
        this.exprs = exprs;
    }

    public void codeGen(Class cl, MethodVisitor mv) {

        // Kontruktor benötigt Opcode INVOKESPECIAL, ansonsten INVOKEVIRTUAL
        int opcode = st.equals("<init>") ? Opcodes.INVOKESPECIAL : Opcodes.INVOKEVIRTUAL;

        // Der Owner ist der Name der Klasse, in welcher die Methode definiert ist
        mv.visitMethodInsn(opcode, cl.ty.name, st, null, false);
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        //TODO unsicher ob das so stimmt!
        return expr.typeCheck(localVars, thisClass);
    }

}

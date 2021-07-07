import java.util.Map;
import org.objectweb.asm.MethodVisitor;

/**
 * Unary - Unäre Operationen
 *
 * Alle Operatoren, die nur einen Operanden haben
 * Darunter fallen z.B. vorangestelltes + und - (Zahl negieren)
 * Oder auch ++, -- und ! (not)
 */
public class Unary extends Expr{
    java.lang.String st;
    Expr expr;

    public Unary(java.lang.String st, Expr expr) {
        this.st = st;
        this.expr = expr;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        // TODO Auto-generated method stub
        return expr.typeCheck(localVars, thisClass);
    }
    
    public void codeGen(Class cl, MethodVisitor mv) {

    }
}

import java.util.Map;
import org.objectweb.asm.MethodVisitor;

/**
 * StmtExprExpr - ?
 *
 * Aus den Folien: Könnte Aufruf einer Methode einer Instanz mit Parametern sein
 * Beispiel aus Folien: (Passt allerdings nicht zur Klassenstruktur!)
 *
 * return x.f(y, z);
 *
 * StmtExprExpr(MethodCall(LocalOrFieldVar("x")),
 *                         "f",
 *                         [LocalOrFieldVar("y"), LocalOrFieldVar("z")]
 *                         )
 */
public class StmtExprExpr extends Expr{
    StmtExpr stmtExpr;

    public StmtExprExpr(StmtExpr stmtExpr) {
        this.stmtExpr = stmtExpr;
    }
    
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return stmtExpr.typeCheck(localVars, thisClass);
    }

    public void codeGen(Class cl, MethodVisitor mv) {
        stmtExpr.codeGen(mv);
    }
}

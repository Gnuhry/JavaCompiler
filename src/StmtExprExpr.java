import java.util.List;

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
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return stmtExpr.typeCheck(localVars, thisClass);
    }

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        System.out.println("[StmtExprExpr] => Capsuled statement type: " + stmtExpr.getClass().getName());
        stmtExpr.codeGen(cl, meth, mv);
    }
}

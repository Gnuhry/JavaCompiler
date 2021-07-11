import java.util.Map;
import org.objectweb.asm.MethodVisitor;

/**
 * StmtExprStmt - Statement-Expression-Statement (?)
 *
 * Aus dem Jay-File:
 *
 * StmtExprStmt: StmtExpr SEMICOLON {
 *     $$ = new StmtExprStmt($1);
 * }
 *
 * Laut Folien kann StmtExprStmt ein Assign kapseln (?)
 */
public class StmtExprStmt extends Stmt{
    StmtExpr expr;

    public StmtExprStmt(StmtExpr expr) {
        this.expr = expr;
    }
    @Override
    public Type typeCheck(Map<String, Type> localVars, Class thisClass) {
        return expr.typeCheck(localVars, thisClass);
    }

    @Override
    public void codeGen(Class cl, MethodVisitor mv) {
        if (expr == null) {
            System.out.println("[StmtExprStmt] => Null");
        } else {
            System.out.println("[StmtExprStmt] => Capsuled statement type: " + expr.getClass().getName());
//            expr.codeGen(cl, mv);
        }

        if (expr != null) {
            expr.codeGen(cl, mv);
        }
    }
}

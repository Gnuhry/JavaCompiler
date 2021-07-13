import java.util.List;

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
    StmtExpr stmtExpr;

    public StmtExprStmt(StmtExpr stmtExpr) {
        this.stmtExpr = stmtExpr;
    }
    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return stmtExpr.typeCheck(localVars, thisClass);
    }

    @Override
    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        if (stmtExpr == null) {
            System.out.println("[StmtExprStmt] => Null");
        } else {
            System.out.println("[StmtExprStmt] => Capsuled statement type: " + stmtExpr.getClass().getName());
//            expr.codeGen(cl, mv);
        }

        if (stmtExpr != null) {
            stmtExpr.codeGen(cl, meth, mv);
        }
    }
}

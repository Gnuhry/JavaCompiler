import java.util.Map;

public class StmtExprStmt extends Stmt{
    StmtExpr expr;

    public StmtExprStmt(StmtExpr expr) {
        this.expr = expr;
    }
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return expr.typeCheck(localVars, thisClass);
    }
}

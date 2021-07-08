import java.util.Map;

public class StmtExprStmt extends Stmt{
    StmtExpr expr;

    public StmtExprStmt(StmtExpr expr) {
        this.expr = expr;
    }
    @Override
    Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return new Type(expr.typeCheck(localVars, thisClass));
    }
}

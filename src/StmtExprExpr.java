import java.util.Map;

public class StmtExprExpr extends Expr{
    StmtExpr stmtExpr;

    public StmtExprExpr(StmtExpr stmtExpr) {
        this.stmtExpr = stmtExpr;
    }
    
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return stmtExpr.typeCheck(localVars, thisClass);
    }
}

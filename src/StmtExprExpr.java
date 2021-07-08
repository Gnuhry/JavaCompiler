import java.util.Map;

public class StmtExprExpr extends Expr{
    StmtExpr stmtExpr;

    public StmtExprExpr(StmtExpr stmtExpr) {
        this.stmtExpr = stmtExpr;
    }
    
    @Override
    Type typeCheck(Map<String, String> localVars, Class thisClass) {
        // TODO Auto-generated method stub
        return new Type("");
    }
}

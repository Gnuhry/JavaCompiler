import java.util.Map;

public class InstVar extends StmtExpr{

    Expr expr;
    java.lang.String st;

    public InstVar(Expr expr, java.lang.String st) {
        this.expr = expr;
        this.st = st;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return expr.typeCheck(localVars, thisClass);
    }
}

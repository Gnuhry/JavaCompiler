import java.util.Map;

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
}

import java.util.Map;

public class Binary extends Expr {
    java.lang.String st;
    Expr expr1;
    Expr expr2;

    public Binary(java.lang.String st, Expr expr1, Expr expr2) {
        this.st = st;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
    @Override
    Type typeCheck(Map<String, String> localVars, Class thisClass) {
        // TODO Auto-generated method stub
        return new Type("");
    }
}

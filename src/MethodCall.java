import java.util.Map;
import java.util.Vector;

public class MethodCall extends StmtExpr{
    Expr expr;
    String st;
    Vector<Expr> exprs;

    public MethodCall(Expr expr, String st, Vector<Expr> exprs) {
        this.expr = expr;
        this.st = st;
        this.exprs = exprs;
    }

    public MethodCall(String st, Vector<Expr> exprs) {
	this.st = st;
	this.exprs = exprs;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        //TODO unsicher ob das so stimmt!
        return expr.typeCheck(localVars, thisClass);
    }

}

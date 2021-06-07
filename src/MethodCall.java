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
}

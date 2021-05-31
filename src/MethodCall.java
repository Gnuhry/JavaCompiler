import java.util.Vector;

public class MethodCall extends StmtExpr{
    Expr expr;
    java.lang.String st;
    Vector<Expr> exprs;

    public MethodCall(Expr expr, java.lang.String st, Vector<Expr> exprs) {
        this.expr = expr;
        this.st = st;
        this.exprs = exprs;
    }
}

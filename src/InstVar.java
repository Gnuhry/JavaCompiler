public class InstVar extends Expr{

    Expr expr;
    java.lang.String st;

    public InstVar(Expr expr, java.lang.String st) {
        this.expr = expr;
        this.st = st;
    }
}

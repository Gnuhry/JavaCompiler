public class Unary extends Expr{
    java.lang.String st;
    Expr expr;

    public Unary(java.lang.String st, Expr expr) {
        this.st = st;
        this.expr = expr;
    }
}

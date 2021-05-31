public class Assign extends StmtExpr{
    java.lang.String st;
    Expr ex;

    public Assign(java.lang.String st, Expr ex) {
        this.st = st;
        this.ex = ex;
    }
}

public class Assign extends StmtExpr {
    LocalOrFieldVar st;
    Expr ex;

    public Assign(LocalOrFieldVar st, Expr ex) {
        this.st = st;
        this.ex = ex;
    }
}

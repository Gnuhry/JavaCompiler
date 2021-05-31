public class If extends Stmt {
    Expr exp;
    Stmt stmt;
    Stmt maybeStmt;
    //TODO Maybe Stmt


    public If(Expr exp, Stmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    public If(Expr exp, Stmt stmt, Stmt maybeStmt) {
        this.exp = exp;
        this.stmt = stmt;
        this.maybeStmt = maybeStmt;
    }
}

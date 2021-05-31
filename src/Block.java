import java.util.Vector;

public class Block extends Stmt {
    Vector<Stmt> stmts;

    public Block(Vector<Stmt> stmts) {
        this.stmts = stmts;
    }
}

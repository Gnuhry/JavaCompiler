import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import java.util.Vector;

public class Block extends Stmt {
    Vector<Stmt> stmts;

    public Block(Vector<Stmt> stmts) {
        this.stmts = stmts;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return new Type("");
    }

    public void codeGen(MethodVisitor mv) {
        for (Stmt stmt : stmts) {
            stmt.codeGen(mv);
        }
    }
}

import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import java.util.Vector;

/**
 * Block - Ein simpler Block von Anweisungen
 * -> z.B. werden alle Anweisungen, die in einer Methode enthalten sind
 * in einem Block gesammelt
 *
 * Status: Vollständig
 */
public class Block extends Stmt {
    Vector<Stmt> stmts;

    public Block(Vector<Stmt> stmts) {
        this.stmts = stmts;
    }

    @Override
    public Type typeCheck(Map<String, Type> localVars, Class thisClass) {
        return new Type("");
    }

    public void codeGen(Class cl, MethodVisitor mv) {
        for (Stmt stmt : stmts) {
            stmt.codeGen(cl, mv);
        }
    }
}

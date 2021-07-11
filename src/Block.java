import java.util.Collections;
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


        // Der Javaparser gibt die Statements in der falschen Reihenfolge aus
        // Annahme: Die Reihenfolge innerhalb gekaplseter Blöcke ist auch falsch
        Collections.reverse(stmts);
        for (Stmt stmt : stmts) {
            System.out.println("[Block] Next block statement type: " + stmt.getClass().getName());
            stmt.codeGen(cl, mv);
        }
    }
}

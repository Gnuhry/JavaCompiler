import org.objectweb.asm.MethodVisitor;

import java.util.List;
import java.util.Vector;

/**
 * Block - Ein simpler Block von Anweisungen
 * -> z.B. werden alle Anweisungen, die in einer Methode enthalten sind
 * in einem Block gesammelt
 *
 * Status: Vollständig
 */
public class Block extends Stmt {
    Vector<Stmt> statements;

    public Block(Vector<Stmt> statements) {
        this.statements = statements;
    }

    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return new Type("");
    }

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {


        // Der Javaparser gibt die Statements in der falschen Reihenfolge aus
        // Annahme: Die Reihenfolge innerhalb gekaplseter Blöcke ist auch falsch
        // Collections.reverse(stmts);
        for (Stmt stmt : statements) {
            System.out.println("[Block] Next block statement type: " + stmt.getClass().getName());
            stmt.codeGen(cl, meth, mv);
        }
    }
}

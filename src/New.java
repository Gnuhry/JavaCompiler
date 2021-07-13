import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;
import java.util.Vector;

/**
 * New - Erzeugung eines neuen Objektes
 *
 * Status:
 */
public class New extends StmtExpr{
    Type type;
    Vector<Expr> expressions;

    public New(Type type, Vector<Expr> expressions) {
        this.type = type;
        this.expressions = expressions;
    }

    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return type;
    }
    
    public void codeGen(Class cl, Method meth, MethodVisitor mv) {

        for (Expr expr : expressions) {
            expr.codeGen(cl, meth, mv);
        }

        // TODO Das scheint mir hier noch alles etwas suspekt

        System.out.println("[New] visitTypeInsn(NEW)");
        mv.visitTypeInsn(Opcodes.NEW, type.name);

        System.out.println("[New] visitInsn(DUP)");
        mv.visitInsn(Opcodes.DUP);

        // In MethodCall wird auch schon der Fall behandelt, dass die Methode <init> heißt
        // Der Methodenaufruf hier ist also vermutlich doppelt oder der Spezialfall
        // sollte aus der MethodCall-Klasse entfernt werden
        // TODO Prüfen, ob Methodenaufruf hier redundant ist

        System.out.println("[New] visitMethodInsn(INVOKESPECIAL)");
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, type.name, "<init>", "()V", false);
    }
}

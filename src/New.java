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
    Type ty;
    Vector<Expr> exprs;

    public New(Type ty, Vector<Expr> exprs) {
        this.ty = ty;
        this.exprs = exprs;
    }

    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return ty;
    }
    
    public void codeGen(Class cl, Method meth, MethodVisitor mv) {

        for (Expr expr : exprs) {
            expr.codeGen(cl, meth, mv);
        }

        mv.visitTypeInsn(Opcodes.NEW, ty.name);
        mv.visitInsn(Opcodes.DUP);

        // In MethodCall wird auch schon der Fall behandelt, dass die Methode <init> heißt
        // Der Methodenaufruf hier ist also vermutlich doppelt oder der Spezialfall
        // sollte aus der MethodCall-Klasse entfernt werden
        // TODO Prüfen, ob Methodenaufruf hier redundant ist
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, ty.name, "<init>", "()V", false);
    }
}

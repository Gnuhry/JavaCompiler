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
        // Typ des Objektes
        this.type = type;

        // Parameter für den Konstruktor
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

        System.out.println("[New] visitTypeInsn(NEW): " + type.name);
        mv.visitTypeInsn(Opcodes.NEW, type.name);

        System.out.println("[New] visitInsn(DUP)");
        mv.visitInsn(Opcodes.DUP);

        System.out.println("[New] visitMethodInsn(INVOKESPECIAL)");
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, type.name, "<init>", "()V", false);
    }
}

import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Vector;

public class New extends StmtExpr{
    Type ty;
    Vector<Expr> exprs;

    public New(Type ty, Vector<Expr> exprs) {
        this.ty = ty;
        this.exprs = exprs;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return ty;
    }
    
    public void codeGen(MethodVisitor mv) {

        for (Expr expr : exprs) {
            expr.codeGen(mv);
        }

        mv.visitTypeInsn(Opcodes.NEW, ty.typ);
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, ty.typ, "<init>", "()V", false);
    }
}

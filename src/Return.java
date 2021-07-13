import java.util.List;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Return - Aus einer Methode herausspringen
 *
 * Status: Vermutlich vollständig
 */
public class Return extends Stmt {
    Expr expr;

    public Return(Expr expr) {
        this.expr = expr;
    }

    public Return() {
    }
    
    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return expr.typeCheck(localVars, thisClass);
    }

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        if (expr == null) {
            System.out.println("[Return] visitInsn(RETURN)");
            mv.visitInsn(Opcodes.RETURN);
        } else {
            System.out.println("[Return] Capsuled Expression: " + expr.getClass().getName());
            expr.codeGen(cl, meth, mv);

            if (expr instanceof Bool || expr instanceof Char || expr instanceof JInteger) {
                System.out.println("[Return] visitInsn(IRETURN)");
                mv.visitInsn(Opcodes.IRETURN);
            } else {
                System.out.println("[Return] visitInsn(ARETURN)");
                mv.visitInsn(Opcodes.ARETURN);
            }
        }
    }
}

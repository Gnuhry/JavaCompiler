import java.util.List;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Return - Aus einer Methode herausspringen
 *
 * Status: Vermutlich vollständig
 */
public class Return extends Stmt {
    Expr exp;

    public Return(Expr exp) {
        this.exp = exp;
    }

    public Return() {
    }
    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return exp.typeCheck(localVars, thisClass);
    }

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        if (exp == null) {
            mv.visitInsn(Opcodes.RETURN);
        } else {
            System.out.println("[Return] Capsuled Expression: " + exp.getClass().getName());
            exp.codeGen(cl, meth, mv);

            if (exp instanceof Bool || exp instanceof Char || exp instanceof JInteger) {
                System.out.println("[Return] Inserting IRETURN");
                mv.visitInsn(Opcodes.IRETURN);
            } else {
                mv.visitInsn(Opcodes.ARETURN);
                System.out.println("[Return] Inserting ARETURN");
            }
        }
    }
}

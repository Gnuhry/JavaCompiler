import java.util.Map;
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
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return exp.typeCheck(localVars, thisClass);
    }

    public void codeGen(MethodVisitor mv) {
        if (exp == null) {
            mv.visitInsn(Opcodes.RETURN);
        } else {
            exp.codeGen(mv);

            if (exp instanceof Bool || exp instanceof Char || exp instanceof JInteger) {
                mv.visitInsn(Opcodes.IRETURN);
            } else {
                mv.visitInsn(Opcodes.ARETURN);
            }
        }
    }
}

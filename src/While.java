import java.util.Map;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class While extends Stmt {
    Expr exp;
    Stmt stmt;

    public While(Expr exp, Stmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return stmt.typeCheck(localVars, thisClass);
    }
    
    public void codeGen(MethodVisitor mv) {
        Label loop = new Label();
        Label end = new Label();

        if (exp instanceof Binary){
            Binary binaryExpression = (Binary) exp;

            switch (binaryExpression.st){
                case "<":
                    mv.visitJumpInsn(Opcodes.IF_ICMPLT, end);
                    break;
                case "<=":
                    mv.visitJumpInsn(Opcodes.IF_ICMPLE, end);
                    break;
                case "==":
                    mv.visitJumpInsn(Opcodes.IF_ICMPEQ, end);
                    break;
                case "!=":
                    mv.visitJumpInsn(Opcodes.IF_ICMPNE, end);
                    break;
                case ">=":
                    mv.visitJumpInsn(Opcodes.IF_ICMPGE, end);
                    break;
                case ">":
                    mv.visitJumpInsn(Opcodes.IF_ICMPGT, end);
                    break;
            }
        } else if(exp instanceof Bool) {
            mv.visitJumpInsn(Opcodes.IFEQ, end);
        }

        mv.visitLabel(loop);
        stmt.codeGen(mv);
        mv.visitJumpInsn(Opcodes.GOTO, loop);
        mv.visitLabel(end);
    }
}

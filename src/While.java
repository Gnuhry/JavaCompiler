import java.util.Map;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * While - While-Schleife
 */
public class While extends Stmt {
    Expr exp;
    Stmt stmt;

    public While(Expr exp, Stmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public Type typeCheck(Map<String, Type> localVars, Class thisClass) {
        return stmt.typeCheck(localVars, thisClass);
    }
    
    /*
     * Die Implementierung deckt sich sehr stark mit der aus 'If'
     * Vermutlich kann man einiges auslagern
     * Darüber hinaus ist auch noch nicht klar, ob das überhaupt stimmt
     */
    public void codeGen(Class cl, MethodVisitor mv) {
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

        // Kann sein, dass die Zeile hier falsch ist und über das Switch-Case muss
        // Man loopt ja sozusagen wieder zum Anfang und muss ja erneut prüfen,
        // ob unsere Bedingung weiterhin erfüllt ist
        mv.visitLabel(loop);

        stmt.codeGen(cl, mv);
        mv.visitJumpInsn(Opcodes.GOTO, loop);
        mv.visitLabel(end);
    }
}

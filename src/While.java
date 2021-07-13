import java.util.List;

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
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return stmt.typeCheck(localVars, thisClass);
    }
    
    /*
     * Die Implementierung deckt sich sehr stark mit der aus 'If'
     * Vermutlich kann man einiges auslagern
     * Darüber hinaus ist auch noch nicht klar, ob das überhaupt stimmt
     */
    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        System.out.println("[While] Compiling While-Loop");
        Label loop = new Label();
        Label end = new Label();

        System.out.println("[While] visitLabel(loop)");
        mv.visitLabel(loop);

        if (exp instanceof Binary){
            Binary binaryExpression = (Binary) exp;

            binaryExpression.leftExpr.codeGen(cl, meth, mv);
            binaryExpression.rightExpr.codeGen(cl, meth, mv);

            switch (binaryExpression.operator){
                case "<":
                    System.out.println("[While] visitJumpInsn(IF_ICMPLT)");
                    mv.visitJumpInsn(Opcodes.IF_ICMPLT, end);
                    break;
                case "<=":
                    System.out.println("[While] visitJumpInsn(IF_ICMPLE)");
                    mv.visitJumpInsn(Opcodes.IF_ICMPLE, end);
                    break;
                case "==":
                    System.out.println("[While] visitJumpInsn(IF_ICMPEQ)");
                    mv.visitJumpInsn(Opcodes.IF_ICMPEQ, end);
                    break;
                case "!=":
                    System.out.println("[While] visitJumpInsn(IF_ICMPNE)");
                    mv.visitJumpInsn(Opcodes.IF_ICMPNE, end);
                    break;
                case ">=":
                    System.out.println("[While] visitJumpInsn(IF_ICMPGE)");
                    mv.visitJumpInsn(Opcodes.IF_ICMPGE, end);
                    break;
                case ">":
                    System.out.println("[While] visitJumpInsn(IF_ICMPGT)");
                    mv.visitJumpInsn(Opcodes.IF_ICMPGT, end);
                    break;
            }
        } else if(exp instanceof Bool) {
            System.out.println("[While] visitJumpInsn(IFEQ)");
            mv.visitJumpInsn(Opcodes.IFEQ, end);
        }

        // Kann sein, dass die Zeile hier falsch ist und über das Switch-Case muss
        // Man loopt ja sozusagen wieder zum Anfang und muss ja erneut prüfen,
        // ob unsere Bedingung weiterhin erfüllt ist

        stmt.codeGen(cl, meth, mv);

        System.out.println("[While] visitJumpInsn(GOTO): loop");
        mv.visitJumpInsn(Opcodes.GOTO, loop);

        System.out.println("[While] visitLabel(end)");
        mv.visitLabel(end);
    }
}

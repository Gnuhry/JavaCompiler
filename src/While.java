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

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        System.out.println("[While] Compiling While-Loop");
        Label loop = new Label();
        Label end = new Label();

        System.out.println("[While] visitLabel(loop)");
        mv.visitLabel(loop);

        if (exp instanceof Binary){
            Binary binaryExpression = (Binary) exp;
            exp.codeGen(cl, meth, mv);
            binaryExpression.insertCmpJumpInstruction(mv, end);
        } else if(exp instanceof Bool) {
            exp.codeGen(cl, meth, mv);
            System.out.println("[While] visitJumpInsn(IFEQ)");
            mv.visitJumpInsn(Opcodes.IFNE, end);
        }

        stmt.codeGen(cl, meth, mv);

        System.out.println("[While] visitJumpInsn(GOTO): loop");
        mv.visitJumpInsn(Opcodes.GOTO, loop);

        System.out.println("[While] visitLabel(end)");
        mv.visitLabel(end);
    }
}

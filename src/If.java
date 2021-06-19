import java.util.Map;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * If - If-Abfrage/Statement
 *
 * Status: Unsicher
 */
public class If extends Stmt {
    Expr exp;
    Stmt stmt;
    Stmt maybeStmt;
    //TODO Maybe Stmt


    public If(Expr exp, Stmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    public If(Expr exp, Stmt stmt, Stmt maybeStmt) {
        this.exp = exp;
        this.stmt = stmt;
        this.maybeStmt = maybeStmt;
    }

    @Override
    public
    Type typeCheck(Map<String, String> localVars, Class thisClass) {
        if(exp.typeCheck(localVars, thisClass).equals(new Type("boolean"))){
			if(stmt.typeCheck(localVars, thisClass).equals(maybeStmt.typeCheck(localVars, thisClass))){
				return stmt.typeCheck(localVars, thisClass);
			}
		}
        throw new RuntimeException("Typecheck Error");
    }

    public void codeGen(MethodVisitor mv) {
        exp.codeGen(mv);

        // Insert here?
        Label end = new Label();
        Label else_label = new Label();

        Label jump_label = maybeStmt == null ? end : else_label;

        if (exp instanceof Binary){
            Binary binaryExpression = (Binary) exp;

            switch (binaryExpression.st){
                case "<":
                    mv.visitJumpInsn(Opcodes.IF_ICMPLT, jump_label);
                    break;
                case "<=":
                    mv.visitJumpInsn(Opcodes.IF_ICMPLE, jump_label);
                    break;
                case "==":
                    mv.visitJumpInsn(Opcodes.IF_ICMPEQ, jump_label);
                    break;
                case "!=":
                    mv.visitJumpInsn(Opcodes.IF_ICMPNE, jump_label);
                    break;
                case ">=":
                    mv.visitJumpInsn(Opcodes.IF_ICMPGE, jump_label);
                    break;
                case ">":
                    mv.visitJumpInsn(Opcodes.IF_ICMPGT, jump_label);
                    break;
            }
        } else if(exp instanceof Bool) {
            mv.visitJumpInsn(Opcodes.IFEQ, jump_label);
        }

        stmt.codeGen(mv);
        mv.visitLabel(else_label);
        maybeStmt.codeGen(mv);
        mv.visitLabel(end);
    }
}

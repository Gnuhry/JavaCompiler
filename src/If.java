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
    Stmt elseStmt;

    public If(Expr exp, Stmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    public If(Expr exp, Stmt stmt, Stmt elseStmt) {
        this.exp = exp;
        this.stmt = stmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public
    Type typeCheck(Map<String, Type> localVars, Class thisClass) {
        if(exp.typeCheck(localVars, thisClass).equals(new Type("boolean"))){
			if(stmt.typeCheck(localVars, thisClass).equals(elseStmt.typeCheck(localVars, thisClass))){
				return stmt.typeCheck(localVars, thisClass);
			}
		}
        throw new RuntimeException("Typecheck Error");
    }

    public void codeGen(Class cl, MethodVisitor mv) {
        System.out.println("Compiling If-Statement");
        Label end = new Label();
        Label else_label = new Label();

        exp.codeGen(cl, mv);

        // Insert here?

        // Fall: If-Expression = false
        Label jump_label = elseStmt == null ? end : else_label;

        if (exp instanceof Binary){
            System.out.println("instanceof Binary");
            Binary binaryExpression = (Binary) exp;

            switch (binaryExpression.st){
                case "<":
                    mv.visitJumpInsn(Opcodes.IF_ICMPLT, jump_label);
                    System.out.println("<");
                    break;
                case "<=":
                    mv.visitJumpInsn(Opcodes.IF_ICMPLE, jump_label);
                    System.out.println("<=");
                    break;
                case "==":
                    mv.visitJumpInsn(Opcodes.IF_ICMPEQ, jump_label);
                    System.out.println("==");
                    break;
                case "!=":
                    mv.visitJumpInsn(Opcodes.IF_ICMPNE, jump_label);
                    System.out.println("!=");
                    break;
                case ">=":
                    mv.visitJumpInsn(Opcodes.IF_ICMPGE, jump_label);
                    System.out.println(">=");
                    break;
                case ">":
                    mv.visitJumpInsn(Opcodes.IF_ICMPGT, jump_label);
                    System.out.println(">");
                    break;
            }
        } else if(exp instanceof Bool) {
            mv.visitJumpInsn(Opcodes.IFEQ, jump_label);
        }

        stmt.codeGen(cl, mv);

        if (elseStmt != null) {
            mv.visitLabel(else_label);
            elseStmt.codeGen(cl, mv);
        }
        mv.visitLabel(end);
    }
}

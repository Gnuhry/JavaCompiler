import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

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

        stmt.codeGen(mv);
        maybeStmt.codeGen(mv);

        mv.visitVarInsn(Opcodes.ILOAD, 1);
        method.visitInsn(Opcodes.ICONST_5);
        method.visitJumpInsn(Opcodes.IF_ICMPGE, end);

        Label jumpLabel = else_statement == null ? end : else_label;

        if (exp instanceof BINARY){
            BINARY binaryExpression = (Binary) exp;

            switch (binaryExpression.operator){
                case "<":
                    method.visitJumpInsn(Opcodes.IF_ICMPLT, jumpLabel);
                    break;
                case "<=":
                    method.visitJumpInsn(Opcodes.IF_ICMPLE, jumpLabel);
                    break;
                case "==":
                    method.visitJumpInsn(Opcodes.IF_ICMPEQ, jumpLabel);
                    break;
                case "!=":
                    method.visitJumpInsn(Opcodes.IF_ICMPNQ, jumpLabel);
                    break;
                case ">=":
                    method.visitJumpInsn(Opcodes.IF_ICMPGE, jumpLabel);
                    break;
                case ">":
                    method.visitJumpInsn(Opcodes.IF_ICMPGT, jumpLabel);
                    break;
            }
        }
    }
}

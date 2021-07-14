import java.util.List;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * If - If-Abfrage/Statement
 *
 * Status: Unsicher
 */
public class If extends Stmt {
    Expr expr;
    Stmt stmt;
    Stmt elseStmt;

    public If(Expr expr, Stmt stmt) {
        this.expr = expr;
        this.stmt = stmt;
    }

    public If(Expr expr, Stmt stmt, Stmt elseStmt) {
        this.expr = expr;
        this.stmt = stmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public
    Type typeCheck(List<Field> localVars, Class thisClass) {
        if(expr.typeCheck(localVars, thisClass).equals(new Type("boolean"))){
			if(stmt.typeCheck(localVars, thisClass).equals(elseStmt.typeCheck(localVars, thisClass))){
				return stmt.typeCheck(localVars, thisClass);
			}
		}
        throw new RuntimeException("Typecheck Error");
    }

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        System.out.println("[If] Compiling If-Statement");
        Label end = new Label();
        Label else_label = new Label();

        expr.codeGen(cl, meth, mv);

        // Existiert ein Else-Statement?
        Label jump_label = elseStmt == null ? end : else_label;

        if (expr instanceof Binary){
            Binary binaryExpression = (Binary) expr;
            binaryExpression.insertCmpJumpInstruction(mv, jump_label);
        } else if(expr instanceof Bool) {
            System.out.println("[If] visitJumpInsn(IFEQ)");
            mv.visitJumpInsn(Opcodes.IFNE, jump_label);
        }

        stmt.codeGen(cl, meth, mv);

        if (elseStmt != null) {
            System.out.println("[If] visitLabel(else)");
            mv.visitLabel(else_label);
            elseStmt.codeGen(cl, meth, mv);
        }
        System.out.println("[If] visitLabel(end)");
        mv.visitLabel(end);
    }
}

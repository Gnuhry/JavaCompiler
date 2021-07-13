import java.util.List;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Return - Aus einer Methode herausspringen
 *
 * Status: Vermutlich vollständig
 */
public class Return extends Stmt {
    Expr expr;

    public Return(Expr expr) {
        this.expr = expr;
    }

    public Return() {
    }
    
    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return expr.typeCheck(localVars, thisClass);
    }

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        if (expr == null) {
            System.out.println("[Return] visitInsn(RETURN)");
            mv.visitInsn(Opcodes.RETURN);
        } else {
            System.out.println("[Return] Capsuled Expression: " + expr.getClass().getName());
            expr.codeGen(cl, meth, mv);

            // Bei Return ist es wichtig, den Typ zu kennen
            String returnType = expr.typeCheck(meth.localVars, cl).name;

            // Falls Expr eine Variable oder Feld ist, muss der genaue Datentyp
            // bestimmt werden
            // TODO Der Kram muss dringend vereinfacht/ausgelagert werden
            if (expr instanceof LocalOrFieldVar) {
                LocalOrFieldVar lOrFieldVar = (LocalOrFieldVar) expr;
                if (returnType.equals("localVar")) {
                    Field f = meth.findLocalVarByName(lOrFieldVar.name);
                    returnType = f.type.name;
                } else if (returnType.equals("fieldVar")) {
                    Field f = cl.findFieldByName(lOrFieldVar.name);
                    returnType = f.type.name;
                }
            }

            switch (returnType) {
                case "boolean":
                case "int":
                case "char":
                    System.out.println("[Return] visitInsn(IRETURN)");
                    mv.visitInsn(Opcodes.IRETURN);
                    break;
                default:
                    System.out.println("[Return] visitInsn(ARETURN)");
                    mv.visitInsn(Opcodes.ARETURN);
            }
        }
    }
}

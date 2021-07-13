import java.util.List;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Binary - Operation mit zwei Operanden, wie z.B. (1 <= 5) oder (1 + 4)
 *
 * Status: Vermutlich unvollständig
 */
public class Binary extends Expr {

    // Operatoren - Mathematische Operatoren, Vergleichsoperatoren, Boolsche Operatoren
    java.lang.String operator;
    Expr leftExpr;
    Expr rightExpr;

    public Binary(java.lang.String operator, Expr leftExpr, Expr rightExpr) {
        this.operator = operator;
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }

    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        if (leftExpr.typeCheck(localVars, thisClass).equals(rightExpr.typeCheck(localVars, thisClass))){
            return leftExpr.typeCheck(localVars, thisClass);
        }else{
            throw new RuntimeException("Typecheck Error");
        }
    }

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        // Vermutlich reichen die beiden Zeilen hier nicht aus
        // Möglicherweise reicht es aber auch einfach aus, wenn hier
        // von beiden Expressions das Ergebnis auf den Stack gepusht wird,
        // sodass in einer übergeordneten Klasse jeweils darauf reagiert werden
        // kann (siehe z.B. das switch-case in 'If' oder 'While'

        System.out.printf("[Binary] Exp1: %s, Exp2: %s, Operator: %s\n", leftExpr.getClass().getName(), rightExpr.getClass().getName(), operator);

        leftExpr.codeGen(cl, meth, mv);
        rightExpr.codeGen(cl, meth, mv);

        // Mathematische Operatoren werden hier bearbeitet
        // Vergleichsoperatoren werden in z.B. Klasse If und While bearbeitet
        // TODO: Ggf. Vergleichsoperatoren hier reinmergen, falls möglich
        switch (operator) {
            case "+": mv.visitInsn(Opcodes.IADD); break;
            case "-": mv.visitInsn(Opcodes.INEG); break;
            case "*": mv.visitInsn(Opcodes.IMUL); break;
            case "/": mv.visitInsn(Opcodes.IDIV); break;
        }
    }
}

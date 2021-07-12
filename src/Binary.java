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
    // TODO Klären, ob bitweise Operatoren benötigt werden
    java.lang.String operator;
    Expr expr1;
    Expr expr2;

    public Binary(java.lang.String operator, Expr expr1, Expr expr2) {
        this.operator = operator;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        if (expr1.typeCheck(localVars, thisClass).equals(expr2.typeCheck(localVars, thisClass))){
            return expr1.typeCheck(localVars, thisClass);
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

        System.out.printf("[Binary] Exp1: %s, Exp2: %s, Operator: %s\n", expr1.getClass().getName(), expr2.getClass().getName(), operator);

        expr1.codeGen(cl, meth, mv);
        expr2.codeGen(cl, meth, mv);

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

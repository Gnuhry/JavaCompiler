import java.util.Map;
import org.objectweb.asm.MethodVisitor;

/**
 * Binary - Operation mit zwei Operanden, wie z.B. (1 <= 5) oder (1 + 4)
 *
 * Status: Vermutlich unvollständig
 */
public class Binary extends Expr {

    // Operatoren - Mathematische Operatoren, Vergleichsoperatoren, Boolsche Operatoren
    // TODO Klären, ob bitweise Operatoren benötigt werden
    java.lang.String st;
    Expr expr1;
    Expr expr2;

    public Binary(java.lang.String st, Expr expr1, Expr expr2) {
        this.st = st;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public Type typeCheck(Map<String, Type> localVars, Class thisClass) {
        if (expr1.typeCheck(localVars, thisClass).equals(expr2.typeCheck(localVars, thisClass))){
            return expr1.typeCheck(localVars, thisClass);
        }else{
            throw new RuntimeException("Typecheck Error");
        }
    }

    public void codeGen(Class cl, MethodVisitor mv) {
        // Vermutlich reichen die beiden Zeilen hier nicht aus
        // Möglicherweise reicht es aber auch einfach aus, wenn hier
        // von beiden Expressions das Ergebnis auf den Stack gepusht wird,
        // sodass in einer übergeordneten Klasse jeweils darauf reagiert werden
        // kann (siehe z.B. das switch-case in 'If' oder 'While'
        expr1.codeGen(cl, mv);
        expr2.codeGen(cl, mv);
    }
}

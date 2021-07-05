import java.util.Map;
import org.objectweb.asm.MethodVisitor;

/**
 * InstVar - Instanz-Variable
 *
 * Aus dem Jay-File:
 * InstVar: TEXT DOT LocalOrFieldVar {
 *     $$ = new InstVar($3, $1);
 * }
 *
 * Status: Fehlt
 *
 * Wenn ich es gerade nicht falsch verstehe: InstVar ist immer ein Feld
 * Somit werden hier Opcodes GETFIELD und PUTFIELD verwendet
 * Frage ist: Was genau macht InstVar? Werden hier Werte einer InstVar
 * zugewiesen oder der Wert davon genommen? Oder keins von beidem?
 */
public class InstVar extends StmtExpr{

    // Bezeichner des Objekts, welcher vor dem Punkt steht
    // Expr ist laut Jay-File aber immer ein LocalOrFieldVar
    Expr expr;

    // Bezeichnung der Variable, auf welche zugegriffen wird
    java.lang.String st;

    public InstVar(Expr expr, java.lang.String st) {
        this.expr = expr;
        this.st = st;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return expr.typeCheck(localVars, thisClass);
    }

    
    public void codeGen(MethodVisitor mv) {

    }
}

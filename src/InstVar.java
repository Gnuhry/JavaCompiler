import java.util.List;

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
public class InstVar extends Expr{

    // Bezeichner des Objekts, welcher vor dem Punkt steht
    // Expr ist laut Jay-File aber immer ein LocalOrFieldVar
    Expr expr;

    // Bezeichnung der Variable, auf welche zugegriffen wird
    java.lang.String name;

    public InstVar(Expr expr, java.lang.String name) {
        this.expr = expr;
        this.name = name;
    }

    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return expr.typeCheck(localVars, thisClass);
    }

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {

    }
}

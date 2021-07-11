import java.util.Map;
import org.objectweb.asm.MethodVisitor;

/**
 * LocalOrFieldVar - Lokale Variable, Feld oder Instanz-Variable
 *
 * Status: Fehlt
 *
 * Siehe Hinweise in Klassen 'Assign' und 'InstVar'
 */
public class LocalOrFieldVar extends Expr {
    java.lang.String name;

    public LocalOrFieldVar(java.lang.String st) {
        this.name = st;
    }
    
    @Override
    public Type typeCheck(Map<String, Type> localVars, Class thisClass) {
        if (thisClass.fields.stream().anyMatch(f -> f.name.equals(name))) {
            return new Type("fieldVar");
        } else if (localVars.keySet().contains(name)) {
            return new Type("localVar");
        } else {
            throw new RuntimeException("Typecheck Error");
        }
    }

    @Override
    public void codeGen(Class cl, MethodVisitor mv) {
    }
}

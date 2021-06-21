import java.util.Map;

/**
 * LocalOrFieldVar - Lokale Variable, Feld oder Instanz-Variable
 *
 * Status: Fehlt
 *
 * Siehe Hinweise in Klassen 'Assign' und 'InstVar'
 */
public class LocalOrFieldVar extends Expr {
    java.lang.String st;

    public LocalOrFieldVar(java.lang.String st) {
        this.st = st;
    }
    
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        if(thisClass.fields.stream().anyMatch(f -> f.name.equals(st))){
            return new Type("fieldVar");
        }else if(localVars.keySet().contains(st)){
            return new Type("localVar");
        }
        else{
            throw new RuntimeException("Typecheck Error");
        }

    }
}

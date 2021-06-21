import java.util.Vector;

/**
 * Parameter - Parameter für eine Methode
 *
 * Status: Fehlt
 *
 * Vermutung: Jeder einzelne Parameter muss mittels PUTFIELD auf den Stack
 * gepusht werden, bevor die Methode aufgerufen wird
 */
public class Parameter {
    Vector<Field> params;

    public Parameter(Vector<Field> params) {
        this.params = params;
    }

    //Kein typeCheck: Parameter von Methoden muessen nicht gleichen Type haben, also kann mMn kein Type der Parameter bestimmt werden.
    
}

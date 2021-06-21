import java.util.Map;

/**
 * This - Die Referenz auf sich selbst
 *
 * 'This' ist bei einem Methodenaufruf immer die lokale Variable mit dem Index 0
 */
public class This extends Expr{
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return thisClass.typeCheck(localVars, thisClass);
    }
}

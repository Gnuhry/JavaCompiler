import java.util.Map;
import org.objectweb.asm.MethodVisitor;

/**
 * Super - Die Übergeordnete Klasse unserer Klasse
 *
 * In unserem Fall vermutlich immer die Klasse 'Object', da wir
 * ansonsten keine Vererbung bei uns im Compiler behandeln
 */
public class Super extends Expr{
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return thisClass.typeCheck(localVars, thisClass);
    }
    
    public void codeGen(Class cl, MethodVisitor mv) {

    }
}

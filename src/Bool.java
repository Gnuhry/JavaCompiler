import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Bool - Boolscher Wert
 *
 * Status: Unsicher
 */
public class Bool extends Expr{
    boolean bo;

    public Bool(boolean bo) {
        this.bo = bo;
    }
    
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return new Type("boolean");
    }
    
    public void codeGen(MethodVisitor mv) {

        // Mittels ILOAD wird ein boolscher Wert von der Variable mit einem bestimmten index
        // auf den Stack gepusht. Es kann sein, dass diese Klasse hier selbst überhaupt
        // gar keinen Code erzeugen soll und nur dazu da ist, um innerhalb anderen Klassen
        // mittels 'instanceof' den Typ zu prüfen
        mv.visitVarInsn(Opcodes.ILOAD, 1);
    }
}

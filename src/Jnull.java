import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Jnull - "Null-Objekt"
 *
 * Status: Unsicher
 */
public class Jnull extends Expr{

    @Override
    public Type typeCheck(Map<String, Type> localVars, Class thisClass) {
        return new Type("null");
    }
    
    // Siehe Hinweis bei der Klasse 'Bool'
    public void codeGen(Class cl, MethodVisitor mv) {
        mv.visitInsn(Opcodes.ACONST_NULL);
    }
}

import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * JInteger - Primitiver Datentyp int
 *
 * Status: Unsicher
 */
public class JInteger extends Expr{
    int in;

    public JInteger(int in) {
        this.in = in;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return new Type("integer");
    }
    
    // Siehe Hinweise bei der Klasse 'Bool'
    public void codeGen(Class cl, MethodVisitor mv) {
        mv.visitLdcInsn(in);
    }
}

import java.util.Map;
import org.objectweb.asm.MethodVisitor;

/**
 * JString - String-Objekt
 *
 * Status: Unsicher
 *
 * Siehe Hinweis bei der Klasse 'Bool'
 */
public class JString extends Expr{
    String st;

    public JString(String st) {
        this.st = st;
    }
    @Override
    public Type typeCheck(Map<String, Type> localVars, Class thisClass) {
        return new Type("string");
    }

    @Override
    public void codeGen(Class cl, MethodVisitor mv) {
        // TODO java.lang.String statt String
        mv.visitLdcInsn(st);
    }
}

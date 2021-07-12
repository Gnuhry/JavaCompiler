import java.util.List;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Jnull - "Null-Objekt"
 *
 * Status: Unsicher
 */
public class Jnull extends Expr{

    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return new Type("null");
    }
    
    // Siehe Hinweis bei der Klasse 'Bool'
    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        mv.visitInsn(Opcodes.ACONST_NULL);

        System.out.println("[Jnull] Pushing null to stack");
    }
}

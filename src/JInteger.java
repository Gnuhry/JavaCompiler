import java.util.List;

import org.objectweb.asm.MethodVisitor;

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
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return new Type("integer");
    }
    
    // Siehe Hinweise bei der Klasse 'Bool'
    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        mv.visitLdcInsn(in);

        System.out.println("[JInteger] Pushing int to stack: " + in);
    }
}

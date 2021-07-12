import java.util.List;

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
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        // Ist die interne Darstellung für den String-Typen
        // Kann sein dass es auch nur "string" ist
        return new Type("java/lang/String");
    }

    @Override
    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        // TODO java.lang.String statt String
        mv.visitLdcInsn(st);

        System.out.println("[JString] Pushing string to stack: " + st);
    }
}

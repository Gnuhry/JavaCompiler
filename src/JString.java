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
    String string;

    public JString(String string) {
        this.string = string;
    }
    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        // TODO Muss evt. durch java/lang/String ausgetauscht werden
        // Problem: Irgendwo kommt manchmal auch Objekte mit Typ "String" ohne Pfadangabe her
        // Idee: In der Type-Klasse
        return new Type("String");
    }

    @Override
    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        mv.visitLdcInsn(string);

        System.out.println("[JString] Pushing string to stack: " + string);
    }
}

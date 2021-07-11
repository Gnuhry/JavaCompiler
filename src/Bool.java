import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Bool - Boolscher Wert
 *
 * Laut Herr Plümicke: Klassen wie Bool und co. beschreiben die Konstanten im Code
 * Somit müssten diese Klassen ebenfalls Code erzeugen können, da der Wert der
 * Konstante vor der Verarbeitung erst auf den Stack gepusht werden muss
 *
 * Status: Unsicher
 */
public class Bool extends Expr{
    boolean bo;

    public Bool(boolean bo) {
        this.bo = bo;
    }
    
    @Override
    public Type typeCheck(Map<String, Type> localVars, Class thisClass) {
        return new Type("boolean");
    }

    public void codeGen(Class cl, MethodVisitor mv) {
        if (bo)
            mv.visitInsn(Opcodes.ICONST_1);
        else
            mv.visitInsn(Opcodes.ICONST_0);

        System.out.println("[Bool] Pushing boolean to stack: " + bo);
    }
}

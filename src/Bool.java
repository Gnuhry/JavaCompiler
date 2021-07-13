import java.util.List;

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
    boolean bool;

    public Bool(boolean bool) {
        this.bool = bool;
    }
    
    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return new Type("boolean");
    }

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        if (bool) {
            System.out.println("[Bool] visitInsn(ICONST_1)");
            mv.visitInsn(Opcodes.ICONST_1);
        }
        else {
            System.out.println("[Bool] visitInsn(ICONST_0)");
            mv.visitInsn(Opcodes.ICONST_0);
        }

        System.out.println("[Bool] Pushing boolean to stack: " + bool);
    }
}

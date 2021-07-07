import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * This - Die Referenz auf sich selbst
 *
 * 'This' ist bei einem Methodenaufruf immer die lokale Variable mit dem Index 0
 *
 * Annahme: Ähnlich Wie Bool & co wird hier nur die Konstante auf den Stack gepusht
 */
public class This extends Expr{
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return thisClass.typeCheck(localVars, thisClass);
    }
    
    public void codeGen(Class cl, MethodVisitor mv) {
        // "this" ist immer die Lokale Variable mit dem Index 0
        // Die wird hier auf den Stack geladen und kann verwendet werden
        mv.visitVarInsn(Opcodes.ALOAD, 0);
    }
}

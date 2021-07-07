import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Char - Character-Variable
 *
 * Status: Unsicher
 */
public class Char extends Expr{
    char ch;

    public Char(char ch) {
        this.ch = ch;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return new Type("char");
    }
    
    // Siehe Hinweise bei der Klasse 'Bool'
    public void codeGen(Class cl, MethodVisitor mv) {
        // Datentyp Char ist 1 Byte lang, BIPUSH packt 1 Byte auf den Stack
        mv.visitVarInsn(Opcodes.BIPUSH, ch);
    }
}

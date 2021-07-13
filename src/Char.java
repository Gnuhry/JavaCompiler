import java.util.List;

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
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return new Type("char");
    }
    
    // Siehe Hinweise bei der Klasse 'Bool'
    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        // Datentyp Char ist 1 Byte lang, BIPUSH packt 1 Byte auf den Stack

        System.out.println("[Char] visitIntInsn(BIPUSH)");
        mv.visitIntInsn(Opcodes.BIPUSH, ch);

        System.out.println("[Char] Pushing char to stack: " + ch);
    }
}

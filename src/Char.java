import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Char extends Expr{
    char ch;

    public Char(char ch) {
        this.ch = ch;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return new Type("char");
    }
    
    public void codeGen(MethodVisitor mv) {
        mv.visitVarInsn(Opcodes.ILOAD, 1);
    }
}

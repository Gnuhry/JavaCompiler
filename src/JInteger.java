import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class JInteger extends Expr{
    int in;

    public JInteger(int in) {
        this.in = in;
    }
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return new Type("integer");
    }
    
    public void codeGen(MethodVisitor mv) {
        mv.visitVarInsn(Opcodes.ILOAD, 1);
    }
}

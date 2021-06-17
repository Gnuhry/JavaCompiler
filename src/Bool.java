import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Bool extends Expr{
    boolean bo;

    public Bool(boolean bo) {
        this.bo = bo;
    }
    
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return new Type("boolean");
    }
    
    public void codeGen(MethodVisitor mv) {
        mv.visitVarInsn(Opcodes.ILOAD, 1);
    }
}

import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class EmptyStmt extends Stmt {
    
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return new Type("null");
    }

    public void codeGen(MethodVisitor mv) {
        mv.visitInsn(Opcodes.NOP);
    }
}
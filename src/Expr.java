import java.util.Map;
import org.objectweb.asm.MethodVisitor;

public abstract class Expr implements TypeInterface {
    private Type type;

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass){
        return this.type;
    }

    public abstract void codeGen(MethodVisitor mv);
}

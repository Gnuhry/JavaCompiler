import java.util.Map;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

/**
 * Stmt - Statement
 *
 * Die Codegenerierung wird von den einzelnen Unterklassen erledigt
 */
public abstract class Stmt implements TypeInterface {
    Type type;
    
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass){
        return type;
    }

    public abstract void codeGen(MethodVisitor mv);
}

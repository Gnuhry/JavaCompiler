import java.util.Map;

import org.objectweb.asm.MethodVisitor;

/**
 * Stmt - Statement
 *
 * Die Codegenerierung wird von den einzelnen Unterklassen erledigt
 */
public abstract class Stmt implements TypeInterface {
    Type type;
    
    @Override
    public Type typeCheck(Map<String, Type> localVars, Class thisClass){
        return type;
    }

    public abstract void codeGen(Class cl, MethodVisitor mv);
}

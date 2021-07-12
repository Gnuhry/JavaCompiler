import java.util.List;

import org.objectweb.asm.MethodVisitor;

/**
 * Stmt - Statement
 *
 * Die Codegenerierung wird von den einzelnen Unterklassen erledigt
 */
public abstract class Stmt implements TypeInterface {
    Type type;
    
    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass){
        return type;
    }

    public abstract void codeGen(Class cl, Method meth, MethodVisitor mv);
}

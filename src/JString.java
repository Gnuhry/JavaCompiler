import java.util.List;

import org.objectweb.asm.MethodVisitor;

/**
 * JString - String-Objekt
 *
 * Status: Unsicher
 */
public class JString extends Expr{
    String string;

    public JString(String string) {
        this.string = string;
    }
    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return new Type("java/lang/String");
    }

    @Override
    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        System.out.println("[JString] visitLdcInsn(string): \"" + string + "\"");
        mv.visitLdcInsn(string);
    }
}

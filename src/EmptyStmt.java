import java.util.List;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * EmptyStmt - Leere Anweisung
 *
 * Status: Vermutlich vollständig
 */
public class EmptyStmt extends Stmt {
    
    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return new Type("");
    }

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        mv.visitInsn(Opcodes.NOP);
    }
}
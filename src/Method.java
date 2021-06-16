import java.util.Map;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Method implements TypeInterface{

    Type retty;
    java.lang.String name;
    Parameter para;
    Stmt stmt;

    public Method(Type retty, java.lang.String name, Parameter para, Stmt stmt) {
        this.retty = retty;
        this.name = name;
        this.para = para;
        this.stmt = stmt;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return retty;
    }
    
    public void codeGen(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, name, retty.typ, null, null);

        mv.visitCode();
        stmt.codeGen(mv);
        mv.visitMaxs(0,0);
        mv.visitEnd();
    }
}

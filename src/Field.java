import java.util.Map;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class Field implements TypeInterface{

    Type ty;
    java.lang.String name;

    public Field(Type ty, java.lang.String name) {
        this.ty = ty;
        this.name = name;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return ty;
    }
    
    public void codeGen(ClassWriter cw) {
        cw.visitField(Opcodes.ACC_PUBLIC, name, ty.typ, null, null);
    }
}

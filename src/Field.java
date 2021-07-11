import java.util.Map;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * Field - Ein Feld bzw. auch Klassenvariable genannt
 *
 * Status: Möglicherweise vollständig
 */
public class Field implements TypeInterface {

    Type ty;
    java.lang.String name;

    public Field(Type ty, java.lang.String name) {
        this.ty = ty;
        this.name = name;
    }

    @Override
    public Type typeCheck(Map<String, Type> localVars, Class thisClass) {
        return ty;
    }
    
    public void codeGen(Class cl, ClassWriter cw) {

        // So wie ich es verstehe, wird dieser Code an der Stelle eingefügt, wo
        // sozusagen ein Feld definiert wird.
        // cw.visitField(Opcodes.ACC_PUBLIC, name, ty.name, null, null);

        // Neuer Code - Nimmt Typdescriptor statt Typname an
        // Ich hatte es mal mit dem Namen des Typs ausprobiert, aber das hat nicht funktioniert
        // Java-VM meckert, dass er dann den jeweiligen internen Typen nicht kennt
        cw.visitField(Opcodes.ACC_PUBLIC, name, ty.getTypeDescriptor(), null, null);
    }
}

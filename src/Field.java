import java.util.List;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * Field - Ein Feld bzw. auch Klassenvariable genannt
 *
 * Die Codegen-Methode wird eigentlich nur dann aufgerufen wird,
 * wenn neue Felder definiert werden.
 *
 * Diese Klasse wird allerdings an vielen Stellen eingesetzt, da man
 * hier auch sehr gut die Informationen zu lokalen Variablen
 * speichern kann.
 *
 * Status: Möglicherweise vollständig
 */
public class Field implements TypeInterface {

    Type type;
    java.lang.String name;

    public Field(Type ty, java.lang.String name) {
        this.type = ty;
        this.name = name;
    }

    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return type;
    }
    
    public void codeGen(Class cl, ClassWriter cw) {
        System.out.println("[Field] visitField(): " + name);
        cw.visitField(Opcodes.ACC_PUBLIC, name, type.getTypeDescriptor(), null, null);
    }
}

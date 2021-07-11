import java.util.Map;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

/**
 * Class - Eine Java-Klasse
 *
 * Status: Möglicherweise vollständing
 *
 * Diese Klasse muss eventuell noch erweitert werden, da man
 * möglicheriwese in der Assign-Klasse noch Daten aus der Klasse
 * benötigt, sofern man einen Wert einer Klassenvariable zuweisen möchte.
 */
public class Class implements TypeInterface{

    Type ty;
    Vector<Field> fields;
    Vector<Method> meth;

    public Class(Type ty, Vector<Field> fields, Vector<Method> meth) {
        this.ty = ty;
        this.fields = fields;
        this.meth = meth;
        codeGen();
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return ty;
    }

    public void codeGen() {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, ty.name, null, "java/lang/Object", null);

        System.out.println(((Object) fields.get(0)).getClass());
        System.out.println(fields);

        for(Field field : fields) {
            System.out.printf("Feld: %s, Typ: %s\n", field.name, field.ty.name);
            field.codeGen(this, cw);
        }

        for(Method m : meth) {
            System.out.printf("Methode: %s\n", m.name);
            m.codeGen(this, cw);
        }
        cw.visitEnd();

        try
        {
            System.out.println("Now try writing");
            writeClassfile(cw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void writeClassfile(ClassWriter cw) throws IOException {
        byte[] bytes = cw.toByteArray();
        System.out.println("Hallo Welt!");
        String className = new ClassReader(bytes).getClassName();
        System.out.println("Writing Class: " + className);
        File outputFile = new File("./", className + ".class");
        FileOutputStream output = new FileOutputStream(outputFile);
        output.write(bytes);
        output.close();
    }
}

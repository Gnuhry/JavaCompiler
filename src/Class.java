import java.lang.management.CompilationMXBean;
import java.util.Collection;
import java.util.Collections;
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
 *
 * TODO: Wenn kein Konstruktor da: Automatisch generieren
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
    public Type typeCheck(Map<String, Type> localVars, Class thisClass) {
        return ty;
    }

    public void codeGen() {
        System.out.println("[Class] Start class compilation: " + ty.name);

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, ty.name, null, "java/lang/Object", null);

        System.out.println("[Class] Compiling fields");
        for(Field field : fields) {
            System.out.printf("[Class] Now compiling field: %s, Typ: %s\n", field.name, field.ty.name);
            field.codeGen(this, cw);
        }

        System.out.println("[Class] Compiling methods");
        for(Method m : meth) {
            System.out.println("--------------------");
            System.out.printf("[Class] Now compiling method: %s\n", m.name);
            m.codeGen(this, cw);
        }
        cw.visitEnd();

        try
        {
            System.out.println("[Class] Try writing class file");
            writeClassfile(cw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hole das Methoden-Objekt mit dem entsprechenden Namen
     * Wird benötigt, um in der MethodCall-Klasse im codegen an den
     * Descriptor der Methode zu kommen.
     *
     * @param name Name der Methode
     * @return Methoden-Objekt, ansonsten null. Null dürfte eigentlich nicht auftreten.
     */
    public Method findMethodByName(String name) {
        for (Method m : meth) {
            if (m.name.equals(name))
                return m;
        }
        return null;
    }

    static void writeClassfile(ClassWriter cw) throws IOException {
        byte[] bytes = cw.toByteArray();
        String className = new ClassReader(bytes).getClassName();
        System.out.println("Writing Class: " + className);
        File outputFile = new File("./", className + ".class");
        FileOutputStream output = new FileOutputStream(outputFile);
        output.write(bytes);
        output.close();
    }
}

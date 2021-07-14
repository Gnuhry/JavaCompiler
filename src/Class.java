import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
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

    Type type;
    Vector<Field> fields;
    Vector<Method> methods;

    public Class(Type type, Vector<Field> fields, Vector<Method> methods) {
        this.type = type;
        this.fields = fields;
        this.methods = methods;
        codeGen();
    }

    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return type;
    }

    public void codeGen() {
        System.out.println("[Class] Start class compilation: " + type.name);

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

        System.out.println("[Class] cw.visit()");
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, type.name, null, "java/lang/Object", null);

        System.out.println("[Class] Compiling fields");
        for(Field field : fields) {
            System.out.printf("[Class] Now compiling field: %s, Typ: %s\n", field.name, field.type.name);
            field.codeGen(this, cw);
        }

        System.out.println("[Class] Compiling constructor");
        MethodVisitor constructor = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        constructor.visitCode();

        constructor.visitVarInsn(Opcodes.ALOAD, 0);
        constructor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        constructor.visitInsn(Opcodes.RETURN);

        constructor.visitMaxs(0,0);
        constructor.visitEnd();




        System.out.println("[Class] Compiling methods");
        for(Method m : methods) {
            System.out.println("--------------------");
            System.out.printf("[Class] Now compiling method: %s\n", m.name);
            m.codeGen(this, cw);
        }

        System.out.println("[Class] cw.visitEnd()");
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
        for (Method m : methods) {
            if (m.name.equals(name))
                return m;
        }
        return null;
    }

    /**
     * Hole das Field-Objekt mit dem entsprechenden Namen
     *
     * @param name Name des Felds
     * @return Field-Objekt, ansonsten null. Null dürfte eigentlich nicht auftreten.
     */
    public Field findFieldByName(String name) {
        for (Field f : fields) {
            if (f.name.equals(name))
                return f;
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

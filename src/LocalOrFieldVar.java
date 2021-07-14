import java.util.List;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * LocalOrFieldVar - Lokale Variable, Feld oder Instanz-Variable
 *
 * Status: Fehlt
 *
 * Siehe Hinweise in Klassen 'Assign' und 'InstVar'
 */
public class LocalOrFieldVar extends Expr {
    java.lang.String name;

    public LocalOrFieldVar(java.lang.String st) {
        this.name = st;
    }
    
    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        if (thisClass.fields.stream().anyMatch(f -> f.name.equals(this.name))) {
            return new Type("fieldVar");
        } else if (localVars.stream().anyMatch(f -> f.name.equals(this.name))) {
            return new Type("localVar");
        } else {
            System.out.println("TYPECHECKING: " + this.name);
            throw new RuntimeException("Typecheck Error");
        }
    }

    @Override
    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        System.out.println("[LocalOrFieldVar] Accessing LOrFVar: " + this.name);

        if (typeCheck(meth.localVars, cl).name.equals("localVar")) {
            Field f = meth.findLocalVarByName(this.name);
            System.out.println("[LocalOrFieldVar] LocalVar has type: " + f.type.name);

            if (f.type.isPrimitive()) {
                System.out.println("[LocalOrFieldVar] visitVarInsn(ILOAD)");
                mv.visitVarInsn(Opcodes.ILOAD, meth.localVars.indexOf(f));
            } else {
                System.out.println("[LocalOrFieldVar] visitVarInsn(ALOAD)");
                mv.visitVarInsn(Opcodes.ALOAD, meth.localVars.indexOf(f));
            }
        } else if (typeCheck(meth.localVars, cl).name.equals("fieldVar")) {
            Field f = cl.findFieldByName(this.name);
            System.out.println("[LocalOrFieldVar] Field has type: " + f.type.name);

            // Wenn es sich um ein Feld handelt, muss man noch das Objekt auf den Stack pushen,
            // in welchem sich das jeweilige Feld befindet.
            // In unserem Fall wird das meistens "this" sein.
            // Mit diesem Hack sollte es allerdings nicht möglich sein, auf die Felder anderer Objekte
            // zuzugreifen und wenn man was als "this.irgendwas" schreibt kanns sein dass es net geht

            System.out.println("[LocalOrFieldVar] visitVarInsn(ALOAD_0)");
            mv.visitVarInsn(Opcodes.ALOAD, 0);

            System.out.println("[LocalOrFieldVar] visitFieldInsn(GETFIELD)");
            mv.visitFieldInsn(Opcodes.GETFIELD, cl.type.name, this.name, f.type.getTypeDescriptor());
        }
    }
}

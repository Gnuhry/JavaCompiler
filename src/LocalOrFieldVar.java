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
        if (thisClass.fields.stream().anyMatch(f -> f.name.equals(name))) {
            return new Type("fieldVar");
//        } else if (localVars.contains(name)) {
        } else if (localVars.stream().anyMatch(f -> f.name.equals(name))) {
            return new Type("localVar");
        } else {
            throw new RuntimeException("Typecheck Error");
        }
    }

    @Override
    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        if (typeCheck(meth.localVars, cl).name.equals("localVar")) {
            Field f = meth.findLocalVarByName(this.name);
            System.out.println("[LocalOrFieldVar] Accessing LocalVar: " + f.ty.name);
            switch (f.ty.name) {
                case "boolean":
                case "int":
                case "char": mv.visitVarInsn(Opcodes.ILOAD, meth.localVars.indexOf(f)); break;
                default:  mv.visitVarInsn(Opcodes.ALOAD, meth.localVars.indexOf(f));
            }
        } else if (typeCheck(meth.localVars, cl).name.equals("fieldVar")) {
            Field f = cl.findFieldByName(this.name);
            System.out.println("[LocalOrFieldVar] Accessing Field: " + f.ty.name);
            mv.visitFieldInsn(Opcodes.GETFIELD, cl.ty.name, this.name, f.ty.getTypeDescriptor());
        }
    }
}

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
            throw new RuntimeException("Typecheck Error");
        }
    }

    @Override
    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        System.out.println("[LocalOrFieldVar] Accessing LOrFVar: " + this.name);

        if (typeCheck(meth.localVars, cl).name.equals("localVar")) {
            Field f = meth.findLocalVarByName(this.name);
            System.out.println("[LocalOrFieldVar] LocalVar has type: " + f.type.name);
            switch (f.type.name) {
                case "boolean":
                case "int":
                case "char":
                    System.out.println("[LocalOrFieldVar] visitVarInsn(ILOAD)");
                    mv.visitVarInsn(Opcodes.ILOAD, meth.localVars.indexOf(f));
                    break;
                default:
                    System.out.println("[LocalOrFieldVar] visitVarInsn(ALOAD)");
                    mv.visitVarInsn(Opcodes.ALOAD, meth.localVars.indexOf(f));
            }
        } else if (typeCheck(meth.localVars, cl).name.equals("fieldVar")) {
            Field f = cl.findFieldByName(this.name);
            System.out.println("[LocalOrFieldVar] Field has type: " + f.type.name);
            System.out.println("[LocalOrFieldVar] visitFieldInsn(GETFIELD)");
            mv.visitFieldInsn(Opcodes.GETFIELD, cl.type.name, this.name, f.type.getTypeDescriptor());
        }
    }
}

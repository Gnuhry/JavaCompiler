import java.util.List;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Assign - Wert zu einer lokalen Variable bzw. einem Feld zuweisen
 *
 * Status: Unvollständig
 */
public class Assign extends StmtExpr {

    // Lokale Variable bzw. Feld, in welchem etwas gespiechert werden soll
    LocalOrFieldVar fieldOrVar;

    // Expression, dessen Ergebnis der Variable bzw. Feld zugewiesen wird
    Expr ex;

    public Assign(LocalOrFieldVar fieldOrVar, Expr ex) {
        this.fieldOrVar = fieldOrVar;
        this.ex = ex;
    }

    /**
     * Typechecking-Methode
     *
     * Gibt in diesem Fall entweder "fieldVar" oder "localVar" zurück.
     * Wird nur benötigt um zu unterscheiden, ob man einen Wert zu einem Feld
     * oder zu einer lokalen Variable zuweist.
     *
     * @param localVars Liste mit allen lokalen Variablen zu einer Methode
     * @param thisClass Die zu kompilierende Klasse
     * @return Entweder "fieldVar" oder "localVar"
     */
    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {

        String varType = fieldOrVar.typeCheck(localVars, thisClass).name;
        String expType = ex.typeCheck(localVars, thisClass).name;

        // Fall: Expression ist lokale Variable oder Feld
        // ex.typeCheck() würde hier "fieldVar" oder "localVar" zurückgeben und nicht den
        // "richtigen Datentyp" der Variable bzw. des Feldes.
        // Muss man stattdessen erst ein Field-Objekt draus machen und davon den Typ nehmen
        if (ex instanceof LocalOrFieldVar) {
            if (expType.equals("fieldVar")) {
                expType = thisClass.findFieldByName(((LocalOrFieldVar) ex).name).ty.name;
            } else if (expType.equals("localVar")) {
                expType = Method.findLocalVarByName(((LocalOrFieldVar) ex).name, localVars).ty.name;
            }
        }


        if (varType.equals("fieldVar")) {
            Field f = thisClass.findFieldByName(fieldOrVar.name);



            System.out.println("1 - " + f);
            // Wenn wir es mit einem Feld zu tun haben, müssen wir prüfen, ob das Feld
            // den selben Typ hat wie das Ergebnis der Expression, welches wir zuweisen wollen
            System.out.println(f.ty.name);
            System.out.println(ex.typeCheck(localVars, thisClass).name);

            if (f != null && f.ty.name.equals(expType)) {
                return new Type("fieldVar");
            }
        } else if (varType.equals("localVar")) {
            Field f = Method.findLocalVarByName(fieldOrVar.name, localVars);
            System.out.println("2 - " + f);
            System.out.println(f.ty.name);
            System.out.println(ex.typeCheck(localVars, thisClass).name);
            // Ähnlich mit lokalen Variablen
            if (f != null && f.ty.name.equals(expType)) {
                return new Type("localVar");
            }
        }

        // Falls Typecheck fehlschlägt
        throw new RuntimeException("Typecheck Error, Type1: " +
                fieldOrVar.typeCheck(localVars, thisClass).name +
                ", Type2: " +
                ex.typeCheck(localVars, thisClass).name);
    }

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {

        System.out.println("[Assign] Type of expression: " + ex.typeCheck(meth.localVars, cl).name);

        if (typeCheck(meth.localVars, cl).name.equals("localVar")) {
            Field f = meth.findLocalVarByName(this.fieldOrVar.name);
            System.out.println("[Assign] Assigning to LocalVar: " + f.ty.name);
            System.out.println("[Assign] Type of field: " + f.ty.name);
            switch (f.ty.name) {
                case "boolean":
                case "int":
                case "char": mv.visitVarInsn(Opcodes.ISTORE, meth.localVars.indexOf(f)); break;
                default:  mv.visitVarInsn(Opcodes.ASTORE, meth.localVars.indexOf(f));
            }
        } else if (typeCheck(meth.localVars, cl).name.equals("fieldVar")) {
            Field f = cl.findFieldByName(this.fieldOrVar.name);
            System.out.println("[Assign] Assigning to Field: " + f.ty.name);
            mv.visitFieldInsn(Opcodes.PUTFIELD, cl.ty.name, f.name, f.ty.getTypeDescriptor());
        }
    }
}

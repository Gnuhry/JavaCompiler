import java.util.List;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Assign - Wert zu einer lokalen Variable bzw. einem Feld zuweisen
 *
 * Status: Unvollständig
 */
public class Assign extends StmtExpr {

    // Lokale Variable bzw. Feld, in welchem etwas gespeichert werden soll
    LocalOrFieldVar fieldOrVar;

    // Expression, dessen Ergebnis der Variable bzw. Feld zugewiesen wird
    Expr expr;

    public Assign(LocalOrFieldVar fieldOrVar, Expr expr) {
        this.fieldOrVar = fieldOrVar;
        this.expr = expr;
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
        String expType = expr.typeCheck(localVars, thisClass).name;

        // Fall: Expression ist lokale Variable oder Feld
        // ex.typeCheck() würde hier "fieldVar" oder "localVar" zurückgeben und nicht den
        // "richtigen Datentyp" der Variable bzw. des Feldes.
        // Muss man stattdessen erst ein Field-Objekt draus machen und davon den Typ nehmen
        if (expr instanceof LocalOrFieldVar) {
            if (expType.equals("fieldVar")) {
                expType = thisClass.findFieldByName(((LocalOrFieldVar) expr).name).type.name;
            } else if (expType.equals("localVar")) {
                expType = Method.findLocalVarByName(((LocalOrFieldVar) expr).name, localVars).type.name;
            }
        }

        if (varType.equals("fieldVar")) {
            Field f = thisClass.findFieldByName(fieldOrVar.name);

            System.out.println("1 - " + f);
            // Wenn wir es mit einem Feld zu tun haben, müssen wir prüfen, ob das Feld
            // den selben Typ hat wie das Ergebnis der Expression, welches wir zuweisen wollen
            System.out.println(f.type.name);
            System.out.println(expr.typeCheck(localVars, thisClass).name);

            if (f != null && f.type.name.equals(expType)) {
                return new Type("fieldVar");
            }
        } else if (varType.equals("localVar")) {
            Field f = Method.findLocalVarByName(fieldOrVar.name, localVars);
            System.out.println("2 - " + f);
            System.out.println(f.type.name);
            System.out.println(expr.typeCheck(localVars, thisClass).name);
            // Ähnlich mit lokalen Variablen
            if (f != null && f.type.name.equals(expType)) {
                return new Type("localVar");
            }
        }

        // Falls Typecheck fehlschlägt
        throw new RuntimeException("Typecheck Error, Type1: " +
                fieldOrVar.typeCheck(localVars, thisClass).name +
                ", Type2: " +
                expr.typeCheck(localVars, thisClass).name);
    }

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {

        System.out.println("[Assign] Type of expression: " + expr.typeCheck(meth.localVars, cl).name);
        String varType = typeCheck(meth.localVars, cl).name;

        // Wenn es sich um ein Feld handelt, muss man noch das Objekt auf den Stack pushen,
        // in welchem sich das jeweilige Feld befindet.
        // In unserem Fall wird das meistens "this" sein.
        // Mit diesem Hack sollte es allerdings nicht möglich sein, auf die Felder anderer Objekte
        // zuzugreifen und wenn man was als "this.irgendwas" schreibt kanns sein dass es net geht
        // TODO Hack entfernen?
        if (varType.equals("fieldVar")) {
            System.out.println("[Assign] visitVarInsn(ALOAD) (HACK!)");
            mv.visitVarInsn(Opcodes.ALOAD, 0);
        }

        expr.codeGen(cl, meth, mv);

        if (varType.equals("localVar")) {
            Field f = meth.findLocalVarByName(this.fieldOrVar.name);
            System.out.println("[Assign] Assigning to LocalVar: " + f.type.name);
            System.out.println("[Assign] Type of field: " + f.type.name);
            switch (f.type.name) {
                case "boolean":
                case "int":
                case "char":
                    System.out.println("[Assign] visitVarInsn(ISTORE)");
                    mv.visitVarInsn(Opcodes.ISTORE, meth.localVars.indexOf(f));
                    break;
                default:
                    System.out.println("[Assign] visitVarInsn(ASTORE)");
                    mv.visitVarInsn(Opcodes.ASTORE, meth.localVars.indexOf(f));
            }
            System.out.println("Index: " + meth.localVars.indexOf(f));
        } else if (varType.equals("fieldVar")) {
            Field f = cl.findFieldByName(this.fieldOrVar.name);
            System.out.println("[Assign] Assigning to Field: " + f.type.name);
            System.out.println("[Assign] visitFieldInsn(PUTFIELD)");
            mv.visitFieldInsn(Opcodes.PUTFIELD, cl.type.name, f.name, f.type.getTypeDescriptor());
        }
    }
}

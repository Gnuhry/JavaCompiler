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

    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {

        if(fieldOrVar.typeCheck(localVars, thisClass).equals(ex.typeCheck(localVars, thisClass))) {
            return fieldOrVar.typeCheck(localVars, thisClass);
        }
        throw new RuntimeException("Typecheck Error");
    }

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {

//        typeCheck()
        System.out.println("[Assign] Assign: " + fieldOrVar.name);

        // ---- Temporärer Wert ----
        // Wenn man einen Wert (= Ergebnis einer Expression) in eine lokale
        // Variable packen will (Felder/Klassenvariablen werden anders behandelt)
        // muss man einen Index für die Variable im Code angeben.
        // Der Index 0 ist immer 'this', die erste erstellte Variable ist 1...
        // TODO Wie komme ich an den richtigen Index der Variable?
        int index = 0;


        // Für den Fall: 'var' ist lokale Variable
        if (ex instanceof Bool || ex instanceof Char || ex instanceof JInteger) {
            mv.visitVarInsn(Opcodes.ISTORE, index);
        } else {
            mv.visitVarInsn(Opcodes.ASTORE, index);
        }


        // Für den Fall: 'var' ist Feld bzw. Klassenvariable
        // Hier muss man dazu den Owner (Name der Klasse, wo sie definiert ist)
        // und den Descriptor (interne Beschreibung des Datentyps angeben)
        // TODO Wie komme ich an den Owner des Felds?
        // TODO Wie komme ich an den Descriptor des Felds?
        mv.visitFieldInsn(Opcodes.PUTFIELD, cl.ty.name, fieldOrVar.name, "I");
    }
}

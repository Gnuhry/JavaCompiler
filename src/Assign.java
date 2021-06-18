import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Assign extends StmtExpr {
    LocalOrFieldVar var;
    Expr ex;

    public Assign(LocalOrFieldVar var, Expr ex) {
        this.var = var;
        this.ex = ex;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {

        if(var.typeCheck(localVars, thisClass).equals(ex.typeCheck(localVars, thisClass))) {
            return var.typeCheck(localVars, thisClass);
        }
        throw new RuntimeException("Typecheck Error");
    }

    public void codeGen(MethodVisitor mv) {

        // ---- Temporärer Wert ----
        // Wenn man einen Wert (= Ergebnis einer Expression) in eine lokale
        // Variable packen will (Felder/Klassenvariablen werden anders behandelt)
        // muss man einen Index für die Variable im Code angeben.
        // Der Index 0 ist immer 'this', die erste erstellte Variable ist 1...
        int index = 0;


        // Für den Fall: 'st' ist lokale Variable
        if (ex instanceof Bool || ex instanceof Char || ex instanceof JInteger) {
            mv.visitVarInsn(Opcodes.ISTORE, index);
        } else {
            mv.visitVarInsn(Opcodes.ASTORE, index);
        }


        // Für den Fall: 'st' ist Feld bzw. Klassenvariable
        // Hier muss man dazu den Owner (Name der Klasse, wo sie definiert ist)
        // und den Descriptor (interne Beschreibung des Datentyps angeben)
        mv.visitFieldInsn(Opcodes.PUTFIELD, "pkg/Bean", st.st, "I");
    }
}

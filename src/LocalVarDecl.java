import java.util.Map;
import org.objectweb.asm.MethodVisitor;

/**
 * LocalVarDecl - Deklaration einer lokalen Variable
 *
 * Status: Fehlt
 *
 * Eine Lokale Variable (Variable innerhalb einer Methode) erhält bei der
 * Deklaration immer einen Index. Dieser muss auch in der visit-Methode angegeben
 * werden, ggf. sollte dieser also in diesem Objekt gespeichert werden
 */
public class LocalVarDecl extends Stmt {
    Type ty;
    java.lang.String st;

    public LocalVarDecl(Type ty, java.lang.String st) {
        this.ty = ty;
        this.st = st;
    }
    @Override
    public Type typeCheck(Map<String, Type> localVars, Class thisClass) {
        return ty;
    }

    public void codeGen(Class cl, MethodVisitor mv) {
        System.out.println("[LocalVarDecl] Define local variable: " + st);
//        String descriptor = "?";
//
//        mv.visitLocalVariable(st, descriptor, null, );
    }
}

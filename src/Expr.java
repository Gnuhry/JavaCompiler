import java.util.Map;
import org.objectweb.asm.MethodVisitor;

/**
 * Expr - Expression
 * Beim 'evaluieren' einer Expression wird sozusagen immer ein Wert mit einem
 * bestimmten Typ erzeugt
 *
 * Beispiel: Die Expression '1 + 5' erzeugt den Wert 6.
 */
public abstract class Expr implements TypeInterface {
    private Type type;

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass){
        return this.type;
    }

    public abstract void codeGen(MethodVisitor mv);
}

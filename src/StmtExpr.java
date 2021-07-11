import java.util.Map;
import org.objectweb.asm.MethodVisitor;

/**
 * StmtExprs - Statement-Expression
 *
 * Kann z.B. folgendes sein: Assign, New, MethodCall, InstVar
 */
public abstract class StmtExpr implements TypeInterface {
    Type type;

    @Override
	public Type typeCheck(Map<String, Type> localVars, Class thisClass) {
        return type;
    }

    public abstract void codeGen(Class cl, MethodVisitor mv);
}

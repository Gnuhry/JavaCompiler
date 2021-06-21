import java.util.Map;

/**
 * StmtExprs - Statement-Expression
 *
 * Kann z.B. folgendes sein: Assign, New, MethodCall, InstVar
 */
public abstract class StmtExpr implements TypeInterface {
    Type type;

    @Override
	public Type typeCheck(Map<String,String> localVars, Class thisClass) {
        return type;
    }
}

import java.util.Map;

public abstract class StmtExpr {
    String type;
	public abstract Type typeCheck(Map<String,String> localVars, Class thisClass);
}

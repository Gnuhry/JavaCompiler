import java.util.Map;

public abstract class Expr {
    Type type;
    abstract Type typeCheck(Map<String, String> localVars, Class thisClass);
}

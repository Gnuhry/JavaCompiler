import java.util.Map;

public abstract class Stmt {
    Type type;
    abstract Type typeCheck(Map<String, String> localVars, Class thisClass);
}

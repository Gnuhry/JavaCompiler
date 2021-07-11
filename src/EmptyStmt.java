import java.util.Map;

public class EmptyStmt extends Stmt {
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return new Type("null");
    }
}
import java.util.Map;

public class EmptyStmt extends Stmt {
    @Override
    Type typeCheck(Map<String, String> localVars, Class thisClass) {
        // TODO Auto-generated method stub
        return new Type("");
    }
}
import java.util.Map;

public class JString extends Expr{
    String st;

    public JString(String st) {
        this.st = st;
    }
    @Override
    Type typeCheck(Map<String, String> localVars, Class thisClass) {
        // TODO Auto-generated method stub
        return new Type("string");
    }
}

import java.util.Map;

public class JString extends Expr{
    String st;

    public JString(String st) {
        this.st = st;
    }
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return new Type("string");
    }
}

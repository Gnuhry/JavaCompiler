import java.util.Map;

public class JInteger extends Expr{
    int in;

    public JInteger(int in) {
        this.in = in;
    }
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return new Type("integer");
    }
}

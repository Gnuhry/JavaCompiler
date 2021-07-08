import java.util.Map;

public class JInteger extends Expr{
    int in;

    public JInteger(int in) {
        this.in = in;
    }
    @Override
    Type typeCheck(Map<String, String> localVars, Class thisClass) {
        // TODO Auto-generated method stub
        new Type("integer");
    }
}

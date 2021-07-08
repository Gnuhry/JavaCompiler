import java.util.Map;

public class Jnull extends Expr{
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return new Type("null");
    }
}

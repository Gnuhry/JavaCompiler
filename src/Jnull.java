import java.util.Map;

public class Jnull extends Expr{
    @Override
    Type typeCheck(Map<String, String> localVars, Class thisClass) {
        // TODO Auto-generated method stub
        return new Type("null");
    }
}

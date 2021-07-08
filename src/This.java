import java.util.Map;

public class This extends Expr{
    @Override
    Type typeCheck(Map<String, String> localVars, Class thisClass) {
        // TODO Auto-generated method stub
        return new Type(thisClass.name);
    }
}

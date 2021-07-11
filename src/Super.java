import java.util.Map;

public class Super extends Expr{
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return thisClass.typeCheck(localVars, thisClass);
    }
}

import java.util.Map;

public class Bool extends Expr{
    boolean bo;

    public Bool(boolean bo) {
        this.bo = bo;
    }
    
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return new Type("boolean");
    }
}

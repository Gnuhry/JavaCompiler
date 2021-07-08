import java.util.Map;

public class Bool extends Expr{
    boolean bo;

    public Bool(boolean bo) {
        this.bo = bo;
    }
    @Override
    Type typeCheck(Map<String, String> localVars, Class thisClass) {
        // TODO Auto-generated method stub
        new Type("boolean");
    }
}

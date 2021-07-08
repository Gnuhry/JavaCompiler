import java.util.Map;

public class LocalOrFieldVar extends Expr{
    java.lang.String st;

    public LocalOrFieldVar(java.lang.String st) {
        this.st = st;
    }
    @Override
    Type typeCheck(Map<String, String> localVars, Class thisClass) {
        // TODO Auto-generated method stub
        return new Type("");
    }
}

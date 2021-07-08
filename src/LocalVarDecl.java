import java.util.Map;

public class LocalVarDecl extends Stmt {
    Type ty;
    java.lang.String st;

    public LocalVarDecl(Type ty, java.lang.String st) {
        this.ty = ty;
        this.st = st;
    }
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return ty;
    }
}

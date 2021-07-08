import java.util.Map;

public class Return extends Stmt {
    Expr exp;

    public Return(Expr exp) {
        this.exp = exp;
    }

    public Return() {
    }
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return exp.typeCheck(localVars, thisClass);
    }
}

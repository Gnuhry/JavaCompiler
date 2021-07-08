import java.util.Map;

public class Return extends Stmt {
    Expr exp;

    public Return(Expr exp) {
        this.exp = exp;
    }

    public Return() {
    }
    @Override
    Type typeCheck(Map<String, String> localVars, Class thisClass) {
        // TODO Auto-generated method stub
        return new Type("");
    }
}

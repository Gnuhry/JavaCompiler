import java.util.Map;

public class While extends Stmt {
    Expr exp;
    Stmt stmt;

    public While(Expr exp, Stmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return stmt.typeCheck(localVars, thisClass);
    }
}

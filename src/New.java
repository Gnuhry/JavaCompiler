import java.util.Map;
import java.util.Vector;

public class New extends StmtExpr{
    Type ty;
    Vector<Expr> exprs;

    public New(Type ty, Vector<Expr> exprs) {
        this.ty = ty;
        this.exprs = exprs;
    }

    @Override
    public String typeCheck(Map<String, String> localVars, Class thisClass) {
        return ty;
    }
}

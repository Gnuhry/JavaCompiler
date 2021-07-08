import java.util.Map;

public class Assign extends StmtExpr {
    LocalOrFieldVar var;
    Expr ex;

    public Assign(LocalOrFieldVar var, Expr ex) {
        this.var = var;
        this.ex = ex;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {

        if(var.typeCheck(localVars, thisClass).equals(ex.typeCheck(localVars, thisClass))) {
            return var.typeCheck(localVars, thisClass);
        }
        return new Type("error");
    }

    }

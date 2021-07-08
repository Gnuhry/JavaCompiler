import java.util.Map;
import java.util.Vector;

public class MethodCall extends StmtExpr{
    Expr expr;
    String st;
    Vector<Expr> exprs;

    public MethodCall(Expr expr, String st, Vector<Expr> exprs) {
        this.expr = expr;
        this.st = st;
        this.exprs = exprs;
    }

    public MethodCall(String st, Vector<Expr> exprs) {
	this.st = st;
	this.exprs = exprs;
    }

    /*@Override
    Type typeCheck(Map<String, String> localVars, Class thisClass) {
        // TODO Auto-generated method stub
        return new Type("");
    }

    public Type typeCheck(Map< String, String> localVars, Class thisClass){
        if (localVars.get(name).equals(ex.typeCheck(localVars, thisClass))){
            String typ = ifStmt.typeCheck(localVars, thisClass);
            return typ;
        }
        else{
            return  "error";
        }
    }*/
}

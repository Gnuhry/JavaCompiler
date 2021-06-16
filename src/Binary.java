import java.util.Map;
import org.objectweb.asm.MethodVisitor;

public class Binary extends Expr {
    java.lang.String st;
    Expr expr1;
    Expr expr2;

    public Binary(java.lang.String st, Expr expr1, Expr expr2) {
        this.st = st;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        if (expr1.typeCheck(localVars, thisClass).equals(expr2.typeCheck(localVars, thisClass))){
            return expr1.typeCheck(localVars, thisClass);
        }else{
            throw new RuntimeException("Typecheck Error");
        }

    public void codeGen(MethodVisitor mv) {
        expr1.codeGen(mv);
        expr2.codeGen(mv);


    }
}

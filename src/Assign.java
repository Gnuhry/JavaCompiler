import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

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
        throw new RuntimeException("Typecheck Error");
    }

    public void codeGen(MethodVisitor mv) {
        mv.visitVarInsn(Opcodes.ISTORE, );
    }
}

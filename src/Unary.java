import java.util.List;

import org.objectweb.asm.MethodVisitor;

/**
 * Unary - Unäre Operationen
 *
 * Alle Operatoren, die nur einen Operanden haben
 * Darunter fallen z.B. vorangestelltes + und - (Zahl negieren)
 * Oder auch ++, -- und ! (not)
 */
public class Unary extends Expr{
    java.lang.String operation;
    Expr expr;

    public Unary(java.lang.String operation, Expr expr) {
        this.operation = operation;
        this.expr = expr;
    }

    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return expr.typeCheck(localVars, thisClass);
    }
    
    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        // TODO Das fehlt komplett
        // Vermutlich nur IINC/INEG/...
    }
}

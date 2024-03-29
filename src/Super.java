import java.util.List;

import org.objectweb.asm.MethodVisitor;

/**
 * Super - Die Übergeordnete Klasse unserer Klasse
 *
 * In unserem Fall vermutlich immer die Klasse 'Object', da wir
 * ansonsten keine Vererbung bei uns im Compiler behandeln
 */
public class Super extends Expr{
    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return thisClass.typeCheck(localVars, thisClass);
    }
    
    public void codeGen(Class cl, Method meth, MethodVisitor mv) {

    }
}

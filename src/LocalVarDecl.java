import java.util.List;

import org.objectweb.asm.MethodVisitor;

/**
 * LocalVarDecl - Deklaration einer lokalen Variable
 *
 * Status: Fehlt
 *
 * Eine Lokale Variable (Variable innerhalb einer Methode) erhält bei der
 * Deklaration immer einen Index. Dieser muss auch in der visit-Methode angegeben
 * werden, ggf. sollte dieser also in diesem Objekt gespeichert werden
 */
public class LocalVarDecl extends Stmt {
    Type type;
    java.lang.String name;

    public LocalVarDecl(Type type, java.lang.String name) {
        this.type = type;
        this.name = name;
    }
    @Override
    public Type typeCheck(List<Field> localVars, Class thisClass) {
        return type;
    }

    public void codeGen(Class cl, Method meth, MethodVisitor mv) {
        meth.localVars.add(new Field(type, name));

        // Wir sagen der Einfachheit halber, dass der Scope in der Methode definiert wird
        // Somit sind alle Variablen vermutlich innerhalb der gesamten Methode verfügbar
        System.out.println("[LocalVarDecl] visitLocalVariable(): " + name);
        mv.visitLocalVariable(this.name, type.getTypeDescriptor(), null, meth.startLabel, meth.endLabel, meth.localVars.size()-1);
    }
}

import java.util.Vector;

public class Class {
    Type ty;
    Vector<Field> fields;
    Vector<Method> meth;


    public Class(Type ty, Vector<Field> fields, Vector<Method> meth) {
        this.ty = ty;
        this.fields = fields;
        this.meth = meth;
    }

    public void typeCheck() {
    }

    public void codeGen() {
    }
}

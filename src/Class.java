import java.util.Map;
import java.util.Vector;

public class Class implements TypeInterface{
    Type ty;
    Vector<Field> fields;
    Vector<Method> meth;


    public Class(Type ty, Vector<Field> fields, Vector<Method> meth) {
        this.ty = ty;
        this.fields = fields;
        this.meth = meth;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return ty;
    }

    public void codeGen() {
    }
}

import java.util.Map;

public class Field implements TypeInterface{
    Type ty;
    java.lang.String name;

    public Field(Type ty, java.lang.String name) {
        this.ty = ty;
        this.name = name;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return ty;
    }
}

import java.util.Map;

public abstract class Expr implements TypeInterface {
    private Type type;

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass){
        return this.type;
    }
}

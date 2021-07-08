import java.util.Map;

public abstract class Stmt implements TypeInterface {
    Type type;
    
    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass){
        return type;
    }
}

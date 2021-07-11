import java.util.Map;

public class Method implements TypeInterface{

    Type retty;
    java.lang.String name;
    Parameter para;
    Stmt stmt;

    public Method(Type retty, java.lang.String name, Parameter para, Stmt stmt) {
        this.retty = retty;
        this.name = name;
        this.para = para;
        this.stmt = stmt;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return retty;
    }
}

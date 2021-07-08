import java.util.Map;

public class Char extends Expr{
    char ch;

    public Char(char ch) {
        this.ch = ch;
    }

    @Override
    public Type typeCheck(Map<String, String> localVars, Class thisClass) {
        return new Type("char");
    }
}

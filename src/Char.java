import java.util.Map;

public class Char extends Expr{
    char ch;

    public Char(char ch) {
        this.ch = ch;
    }
    @Override
    Type typeCheck(Map<String, String> localVars, Class thisClass) {
        // TODO Auto-generated method stub
        return new Type("");
    }
}

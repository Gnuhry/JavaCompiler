import java.util.List;

public interface TypeInterface {

    /**
     * Typechecking-Methode
     *
     * @param localVars Eine Liste mit allen lokalen Variablen einer Methode.
     *                  Wird benötigt, um den Index einer Variable innerhalb einer Methode zu bestimmen.
     *                  Obwohl es sich hier um lokale Variablen und nicht um Felder handelt,
     *                  eignet sich die Klasse Field besser, da sie sowohl den Namen als auch Typ enthält.
     * @param thisClass Klassen-Objekt (cl)
     * @return
     */
    public Type typeCheck(List<Field> localVars, Class thisClass);
}

/**
 * Type - Datentyp
 *
 * Status: Unsicher
 *
 * Vermutung: Die Klasse selbst erzeugt keinen Code und wird in anderen
 * Klassen nur zum Typechecking genutzt
 */
public class Type {
    java.lang.String typ;

    public Type(java.lang.String typ) {
        this.typ = typ;
    }

    @Override
    public boolean equals(Object obj) {
        return typ.equals(((Type) obj).typ);
    }
}

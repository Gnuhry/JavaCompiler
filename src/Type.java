/**
 * Type - Datentyp
 *
 * Status: Unsicher
 *
 * Vermutung: Die Klasse selbst erzeugt keinen Code und wird in anderen
 * Klassen nur zum Typechecking genutzt
 */
public class Type {

    // Name des Typs
    java.lang.String name;

    /**
     * Neues Type-Objekt erstellen
     * @param name Name des Typs
     */
    public Type(java.lang.String name) {
        this.name = name;

        switch (this.name) {
            case "String": this.name = "java/lang/String"; break;
            case "Object": this.name = "java/lang/Object"; break;
        }
    }

    /**
     * Gebe den Deskriptor des Typs zurück.
     *
     * Siehe auch: https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.3
     *
     * @return Descriptor des Typs
     */
    public String getTypeDescriptor() {
        switch (name) {
            case "int": return "I";
            case "char": return "C";
            case "boolean": return "Z";
            case "void": return "V";

            case "byte":
            case "small":
            case "float":
            case "double":
            case "long":
                System.err.println("Der Java-Compiler unterstützt folgenden Datentyp nicht: " + name);
                System.exit(1);

            default: // Annahme: Alles, was hier ankommt, ist eine Klasse
                return "L" + name + ";";
        }
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Type) obj).name);
    }

    public boolean isPrimitive() {
        return isPrimitive(this.name);
    }

    public static boolean isPrimitive(String type) {
        switch (type) {
            case "int":
            case "char":
            case "boolean":
                return true;
            default:
                return false;
        }
    }
}

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

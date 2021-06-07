public class main {
    public static void main (String [] args) {
	javascanner scanner =new javascanner(new java.io.InputStreamReader (System.in));
	javaparser parser = new javaparser() ;
	try {
	    Class javaClass  = (Class) parser.yyparse(scanner);
	    System.out.println("Title: " + javaClass);
	}catch (Exception e) {e.printStackTrace();}
    }
}

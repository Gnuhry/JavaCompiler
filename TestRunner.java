/**
 * Test-Runner Klasse
 *
 * Da unser Compiler kein "Static" kann, kann er auch keine Main-Methode haben.
 * Somit muss die Klasse von einer anderen Klasse erzeugt und aufgerufen werden.
 */
public class TestRunner {
    public static void main(String[] args) {
        TestClass tc = new TestClass();
        System.out.println(tc.silas());
    }
}
/**
 * Test-Runner Klasse
 *
 * Da unser Compiler kein "Static" kann, kann er auch keine Main-Methode haben.
 * Somit muss die Klasse von einer anderen Klasse erzeugt und aufgerufen werden.
 */
public class TestRunner {
    public static void main(String[] args) {
        TestClass tc = new TestClass();
        System.out.println("silas(): " + tc.silas());
        System.out.println("test2(3, Hallo)");
        tc.test2(3, "Hallo");
        System.out.println("test32(100)" + tc.test32(100));
        System.out.println("test32(100)" + tc.test32(2));

        System.out.println("addition(5 + 9): " + tc.addition(5, 9));
    }
}
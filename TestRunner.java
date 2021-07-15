/**
 * Test-Runner Klasse
 *
 * Da unser Compiler kein "Static" kann, kann er auch keine Main-Methode haben.
 * Somit muss die Klasse von einer anderen Klasse erzeugt und aufgerufen werden.
 */
public class TestRunner {
    public static void main(String[] args) {
        TestClass tc = new TestClass();
        System.out.println("intEquality(1, 1) -> true: " + tc.intEquality(1, 1));
        System.out.println("intEquality(1, 2) -> false: " + tc.intEquality(1, 2));
        System.out.println("setT(\"Test\"): ");
        tc.setT("Test");
        System.out.println("getT() -> Test: " + tc.getT());
        System.out.println("maximum(1, 3) -> 3: " + tc.maximum(1, 3));
        System.out.println("minimum(1, 3) -> 1: " + tc.minimum(1, 3));
        System.out.println("methodCall(\"Test\") -> Second - Test: " + tc.methodCall("Test"));
        System.out.println("methodDataTypes(1.0, false, 1, \"Test\"): ");
        tc.methodDataTypes(1.0, false, 1, "Test");
        System.out.println("whileTo6() -> 6: " + tc.whileTo6());
    }
}
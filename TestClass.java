class TestClass{
    int test;
    String i;

    void test(int a, String b) {
        System.out.println(a + b);
    }

    int test2(int a, String b) {
        return a;
    }

    String t;

    int test32(int a) {
        if (true || false) {
            return 5;
        } else
            System.out.println("Else");
        t = t;
        int i;
        t = null;
        t = "5";
        i = 3;
        Object o = new Object();
        super.toString();
        this.test2(5, "Hallo");
        while (i > 0) {
            System.out.println("Schleifendurchgang");
            i = 0;
        }

        return 69;
    }
}
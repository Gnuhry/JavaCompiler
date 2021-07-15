class TestClass{

    String t;

    boolean intEquality(int a, int b) {
        return a == b;
    }

    void setT(String test) {
        t = test;
    }

    int minimum(int a, int b){
        if(a<b){
            return a;
        }else{
            return b;
        }
    }

    int maximum(int a, int b){
        if(a>b){
            return a;
        }else{
            return b;
        }
    }
    
    String methodCall(String test) {
        return secondMethod(test);
    }
    
    String secondMethod(String test) {
        return "Pong";
    }
    
    void methodDataTypes(boolean b, int i, String s) {
        return;
    }

    int whileTo6(){
        int x1;
        int x2;
        x1 = 1;
        x2 = 5;
        while(x1<=x2){
            x1 = x1+1;
        }

        return x1;
    }

    TestClass objectCreation() {
	TestClass tc;
	tc = new TestClass();
	return tc;
    }

    String getT() {
        return t;
    }
}

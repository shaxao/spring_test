package natuc.lambda;

public class Test2 {
    public static void main(String[] args) {
        NoReturnNum noReturnNum = Test::addNum;
        int method = noReturnNum.method(10, 20);
        System.out.println(method);
    }
}

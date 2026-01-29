class test {
    public static int a;
    public void show(int b) {
        System.out.println("The value of a is: " + a);
        System.out.println("The value of b is: " + b);
        System.out.println("The sum of a and b is: " + (a+b));
    }
}

public class MainTest {
    public static void main(String[] args) {
        test.a = 10;
        test t = new test();
        t.show(20);
    }
}

public class Calculator {
    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            return -1;
        }
        return a / b;
    }

    public static void main(String[] args) {
        // Calculator calculator = new Calculator();
        // System.out.println("Addition: " + calculator.add(110, 5));
        // System.out.println("Subtraction: " + calculator.subtract(10, 5));
        // System.out.println("Multiplication: " + calculator.multiply(10, 5));
        // System.out.println("Division: " + calculator.divide(10, 5));
    }
}

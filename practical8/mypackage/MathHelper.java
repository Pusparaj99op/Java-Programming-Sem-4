// User-defined package: mypackage
// This class provides basic math utility methods.

package mypackage;

public class MathHelper {

    // Add two integers
    public int add(int a, int b) {
        return a + b;
    }

    // Subtract b from a
    public int subtract(int a, int b) {
        return a - b;
    }

    // Multiply two integers
    public int multiply(int a, int b) {
        return a * b;
    }

    // Divide a by b (integer division)
    public double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Division by zero.");
            return 0;
        }
        return a / b;
    }

    // Check if a number is prime
    public boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}

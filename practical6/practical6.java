// Aim: Write a program to implement the concepts of Exception Handling in Java.

// Exception Handling concepts covered:
// 1. try-catch-finally
// 2. Multiple catch blocks
// 3. Checked Exception (using throws)
// 4. Unchecked Exception
// 5. Nested try-catch

import java.io.*;

// =================================================================================================
// 1. Basic try-catch-finally

class BasicException {
    static void demo() {
        System.out.println("--- try-catch-finally ---");
        try {
            int result = 10 / 0;  // ArithmeticException
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
        } finally {
            System.out.println("finally block always executes.");
        }
    }
}

// =================================================================================================
// 2. Multiple catch blocks

class MultipleCatch {
    static void demo() {
        System.out.println("\n--- Multiple catch blocks ---");
        int[] arr = {1, 2, 3};

        // Test 1: ArrayIndexOutOfBoundsException
        try {
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Caught generic Exception: " + e.getMessage());
        }

        // Test 2: NumberFormatException
        try {
            int num = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException: " + e.getMessage());
        }

        // Test 3: NullPointerException
        try {
            String s = null;
            System.out.println(s.length());
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: " + e.getMessage());
        }
    }
}

// =================================================================================================
// 3. Checked Exception using throws

class FileReader6 {
    static void readFile(String filename) throws IOException {
        // Simulating file read — throws checked IOException
        throw new IOException("File not found: " + filename);
    }

    static void demo() {
        System.out.println("\n--- Checked Exception with throws ---");
        try {
            readFile("data.txt");
        } catch (IOException e) {
            System.out.println("Caught IOException: " + e.getMessage());
        }
    }
}

// =================================================================================================
// 4. Nested try-catch

class NestedTryCatch {
    static void demo() {
        System.out.println("\n--- Nested try-catch ---");
        try {
            System.out.println("Outer try block");
            try {
                int[] arr = new int[2];
                arr[5] = 10;  // ArrayIndexOutOfBoundsException
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Inner catch: " + e.getMessage());
            }
            int result = 10 / 0;  // ArithmeticException in outer try
        } catch (ArithmeticException e) {
            System.out.println("Outer catch: " + e.getMessage());
        } finally {
            System.out.println("Outer finally block.");
        }
    }
}

// =================================================================================================
// Runner class — entry point

class Runner6 {
    public static void main(String[] args) {
        BasicException.demo();
        MultipleCatch.demo();
        FileReader6.demo();
        NestedTryCatch.demo();
    }
}

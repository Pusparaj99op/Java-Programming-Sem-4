// Aim: Write a program on Packages (pre-defined & user-defined packages) in Java.

// Pre-defined packages used:
//   java.util.Scanner  — for user input
//   java.util.ArrayList — dynamic list
//   java.util.Arrays   — array utility

// User-defined package used:
//   mypackage.MathHelper — custom math utility class

// ---- COMPILE AND RUN INSTRUCTIONS ----------------------------------------
// Step 1: Compile MathHelper (creates mypackage/MathHelper.class)
//         javac -d . mypackage/MathHelper.java
//         (run from inside the practical8/ directory)
//
// Step 2: Compile main file
//         javac practical8.java
//
// Step 3: Run
//         java Runner8
// --------------------------------------------------------------------------

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import mypackage.MathHelper;

class Runner8 {
    public static void main(String[] args) {

        // ---- Pre-defined package: java.util.Scanner ----
        System.out.println("===== Pre-defined Package: java.util.Scanner =====");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your age : ");
        int age = sc.nextInt();
        System.out.println("Hello, " + name + "! You are " + age + " years old.");

        // ---- Pre-defined package: java.util.ArrayList ----
        System.out.println("\n===== Pre-defined Package: java.util.ArrayList =====");
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Mango");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Apple");
        System.out.println("Fruits list : " + fruits);
        fruits.remove("Banana");
        System.out.println("After remove: " + fruits);
        System.out.println("Size        : " + fruits.size());

        // ---- Pre-defined package: java.util.Arrays ----
        System.out.println("\n===== Pre-defined Package: java.util.Arrays =====");
        int[] numbers = {5, 3, 8, 1, 9, 2, 7};
        System.out.println("Before sort: " + Arrays.toString(numbers));
        Arrays.sort(numbers);
        System.out.println("After sort : " + Arrays.toString(numbers));
        System.out.println("Search 7   : index " + Arrays.binarySearch(numbers, 7));

        // ---- User-defined package: mypackage.MathHelper ----
        System.out.println("\n===== User-defined Package: mypackage.MathHelper =====");
        MathHelper mh = new MathHelper();
        System.out.println("Add(10, 5)      : " + mh.add(10, 5));
        System.out.println("Subtract(10, 5) : " + mh.subtract(10, 5));
        System.out.println("Multiply(4, 6)  : " + mh.multiply(4, 6));
        System.out.println("Divide(10, 4)   : " + mh.divide(10, 4));
        System.out.println("isPrime(7)      : " + mh.isPrime(7));
        System.out.println("isPrime(10)     : " + mh.isPrime(10));

        sc.close();
    }
}

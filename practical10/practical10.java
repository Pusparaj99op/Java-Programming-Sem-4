// Aim: Write a program on Vector class & Array in Java.

// Topics covered:
// 1. 1D Array — declaration, initialisation, traversal
// 2. 2D Array — matrix operations
// 3. Vector class — add, get, remove, size, contains, elements()
// 4. Comparison: Array vs Vector

import java.util.Vector;
import java.util.Enumeration;

// =================================================================================================
// 1. 1D Array demonstration

class ArrayDemo {
    static void oneDimension() {
        System.out.println("===== 1D Array =====");
        int[] marks = {85, 92, 78, 90, 88};  // declaration and initialization
        System.out.print("Marks: ");
        for (int m : marks) {
            System.out.print(m + " ");
        }
        System.out.println();

        // Find maximum
        int max = marks[0];
        for (int m : marks) {
            if (m > max) max = m;
        }
        System.out.println("Maximum mark: " + max);

        // Sum and average
        int sum = 0;
        for (int m : marks) sum += m;
        System.out.println("Sum    : " + sum);
        System.out.printf("Average: %.2f%n", (double) sum / marks.length);
    }

    static void twoDimension() {
        System.out.println("\n===== 2D Array (Matrix) =====");
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Matrix:");
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }

        // Diagonal sum
        int diagSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            diagSum += matrix[i][i];
        }
        System.out.println("Diagonal sum: " + diagSum);
    }
}

// =================================================================================================
// 2. Vector class demonstration

class VectorDemo {
    static void demo() {
        System.out.println("\n===== Vector Class =====");

        Vector<String> v = new Vector<>();  // creates an empty vector

        // add() — add elements
        v.add("Pranay");
        v.add("Sita");
        v.add("Raju");
        v.add("Neha");
        System.out.println("Vector after add: " + v);

        // get() — access by index
        System.out.println("Element at index 1: " + v.get(1));

        // size() — number of elements
        System.out.println("Size: " + v.size());

        // contains() — check presence
        System.out.println("Contains 'Raju'? " + v.contains("Raju"));

        // set() — update element
        v.set(2, "Rohan");
        System.out.println("After set(2,'Rohan'): " + v);

        // remove() — remove element
        v.remove("Sita");
        System.out.println("After remove('Sita'): " + v);

        // elements() — Enumeration traversal
        System.out.print("Enumeration traversal: ");
        Enumeration<String> en = v.elements();
        while (en.hasMoreElements()) {
            System.out.print(en.nextElement() + " ");
        }
        System.out.println();

        // Vector of integers
        System.out.println("\n--- Integer Vector ---");
        Vector<Integer> nums = new Vector<>(3, 2);  // initial capacity 3, grows by 2
        for (int i = 10; i <= 50; i += 10) nums.add(i);
        System.out.println("Integer vector: " + nums);
        System.out.println("Capacity      : " + nums.capacity());
        nums.removeElementAt(2);
        System.out.println("After removeElementAt(2): " + nums);
    }
}

// =================================================================================================
// Runner class — entry point

class Runner10 {
    public static void main(String[] args) {
        ArrayDemo.oneDimension();
        ArrayDemo.twoDimension();
        VectorDemo.demo();

        // Comparison summary
        System.out.println("\n===== Array vs Vector =====");
        System.out.println("Array   : Fixed size, faster, stores primitives and objects.");
        System.out.println("Vector  : Dynamic size, thread-safe (synchronized), stores only objects.");
    }
}

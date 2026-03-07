# Practical 10: Vector Class & Array in Java

## Aim
To write a program on Vector class & Array in Java.

## Theory

### Arrays
An **array** is a fixed-size, ordered collection of elements of the same data type. Arrays are stored in contiguous memory locations.
- **Declaration**: `int[] arr = new int[5];`
- **Initialisation**: `int[] arr = {10, 20, 30};`
- **Access**: `arr[0]` (zero-indexed)
- Size is fixed at creation; cannot grow or shrink.

### Vector Class
`java.util.Vector` is a **dynamic array** — it can grow and shrink at runtime. It is similar to `ArrayList` but is **synchronized** (thread-safe).

| Method | Description |
|--------|-------------|
| `add(e)` | Adds element at end |
| `get(i)` | Returns element at index i |
| `set(i, e)` | Replaces element at index i |
| `remove(e)` | Removes first occurrence of element |
| `removeElementAt(i)` | Removes element at given index |
| `size()` | Returns number of elements |
| `capacity()` | Returns current storage capacity |
| `contains(e)` | Checks if element exists |
| `elements()` | Returns Enumeration for traversal |

### Array vs Vector

| Feature | Array | Vector |
|---------|-------|--------|
| Size | Fixed | Dynamic |
| Type | Primitives & Objects | Objects only |
| Thread Safety | Not synchronized | Synchronized |
| Performance | Faster | Slightly slower |
| Package | None (built-in) | `java.util` |

## Algorithm
1. **1D Array**: Declare and initialise a marks array. Traverse to print values, find maximum, compute sum and average.
2. **2D Array**: Define a 3×3 matrix. Print all elements. Compute diagonal sum.
3. **Vector**: Create a `Vector<String>`, demonstrate `add`, `get`, `size`, `contains`, `set`, `remove`, `elements()` (Enumeration). Create `Vector<Integer>` with custom capacity.
4. Print Array vs Vector comparison.

## Code

### practical10.java
```java
// Aim: Write a program on Vector class & Array in Java.

import java.util.Vector;
import java.util.Enumeration;

class ArrayDemo {
    static void oneDimension() {
        int[] marks = {85, 92, 78, 90, 88};
        System.out.print("Marks: "); for (int m : marks) System.out.print(m + " "); System.out.println();
        int max = marks[0], sum = 0;
        for (int m : marks) { if (m > max) max = m; sum += m; }
        System.out.println("Max: " + max + " | Sum: " + sum + " | Avg: " + (double)sum/marks.length);
    }
    static void twoDimension() {
        int[][] m = {{1,2,3},{4,5,6},{7,8,9}};
        for (int[] row : m) { for (int v : row) System.out.printf("%4d",v); System.out.println(); }
        int d = 0; for (int i=0;i<m.length;i++) d += m[i][i];
        System.out.println("Diagonal sum: " + d);
    }
}

class VectorDemo {
    static void demo() {
        Vector<String> v = new Vector<>();
        v.add("Pranay"); v.add("Sita"); v.add("Raju"); v.add("Neha");
        System.out.println("Vector: " + v);
        System.out.println("get(1): " + v.get(1) + " | size: " + v.size() + " | contains Raju: " + v.contains("Raju"));
        v.set(2,"Rohan"); v.remove("Sita");
        System.out.println("After set+remove: " + v);
        Enumeration<String> en = v.elements();
        while (en.hasMoreElements()) System.out.print(en.nextElement() + " ");
        System.out.println();
    }
}

class Runner10 {
    public static void main(String[] args) {
        System.out.println("===== 1D Array =====");      ArrayDemo.oneDimension();
        System.out.println("\n===== 2D Array =====");     ArrayDemo.twoDimension();
        System.out.println("\n===== Vector Class =====");  VectorDemo.demo();
        System.out.println("\nArray: fixed size, fast. Vector: dynamic, synchronized.");
    }
}
```

## Output
```
===== 1D Array =====
Marks: 85 92 78 90 88
Max: 92 | Sum: 433 | Avg: 86.6

===== 2D Array =====
   1   2   3
   4   5   6
   7   8   9
Diagonal sum: 15

===== Vector Class =====
Vector: [Pranay, Sita, Raju, Neha]
get(1): Sita | size: 4 | contains Raju: true
After set+remove: [Pranay, Rohan, Neha]
Pranay Rohan Neha

Array: fixed size, fast. Vector: dynamic, synchronized.
```

## Result
The program successfully demonstrates arrays (1D and 2D) and the `Vector` class in Java. Arrays provide fixed-size indexed storage with direct element access, while `Vector` provides dynamic resizing with methods like `add`, `remove`, `set`, and `contains`. The `Enumeration` interface is also demonstrated for traversal of vector elements.

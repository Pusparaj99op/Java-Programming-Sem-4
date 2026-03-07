# Practical 8: Packages in Java

## Aim
To write a program on Packages (pre-defined & user-defined packages) in Java.

## Theory
A **package** in Java is a namespace that organises a set of related classes and interfaces. Packages help avoid naming conflicts, control access, and make code easier to search and maintain.

**Types of Packages:**

1. **Pre-defined (Built-in) Packages**: Provided by the Java API.
   - `java.lang` — automatically imported; contains `String`, `Math`, `System`, etc.
   - `java.util` — contains `Scanner`, `ArrayList`, `Arrays`, `HashMap`, etc.
   - `java.io` — contains file I/O classes.

2. **User-defined Packages**: Created by the programmer.
   - Declared using the `package` keyword at the top of a Java file.
   - Imported in other files using `import packagename.ClassName;`.

**Package declaration syntax:**
```java
package mypackage;
public class MathHelper { ... }
```

**Import syntax:**
```java
import mypackage.MathHelper;
```

**Compile and run with user-defined package:**
```
javac -d . mypackage/MathHelper.java   // compile package class
javac practical8.java                  // compile main class
java Runner8                           // run
```

## Algorithm
1. Create `mypackage/MathHelper.java` with `package mypackage;` declaration and methods: `add`, `subtract`, `multiply`, `divide`, `isPrime`.
2. Compile `MathHelper.java` with `-d .` to create the package directory structure.
3. In `practical8.java`:
   - Import `java.util.Scanner` and read `name` and `age`.
   - Use `java.util.ArrayList` to add/remove fruits and display.
   - Use `java.util.Arrays` to sort and search an integer array.
   - Import `mypackage.MathHelper` and call its methods.
4. Compile and run `practical8.java`.

## Code

### mypackage/MathHelper.java
```java
package mypackage;

public class MathHelper {
    public int    add(int a, int b)          { return a + b; }
    public int    subtract(int a, int b)     { return a - b; }
    public int    multiply(int a, int b)     { return a * b; }
    public double divide(double a, double b) { if (b == 0) { System.out.println("Error: Division by zero."); return 0; } return a / b; }
    public boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) if (n % i == 0) return false;
        return true;
    }
}
```

### practical8.java
```java
// Aim: Write a program on Packages (pre-defined & user-defined packages) in Java.

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import mypackage.MathHelper;

class Runner8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: "); String name = sc.nextLine();
        System.out.print("Enter your age : "); int age = sc.nextInt();
        System.out.println("Hello, " + name + "! You are " + age + " years old.");

        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Mango"); fruits.add("Banana"); fruits.add("Orange"); fruits.add("Apple");
        System.out.println("Fruits: " + fruits);
        fruits.remove("Banana");
        System.out.println("After remove: " + fruits);

        int[] numbers = {5, 3, 8, 1, 9, 2, 7};
        Arrays.sort(numbers);
        System.out.println("Sorted: " + Arrays.toString(numbers));

        MathHelper mh = new MathHelper();
        System.out.println("Add(10,5)=" + mh.add(10,5) + " isPrime(7)=" + mh.isPrime(7));
        sc.close();
    }
}
```

## Output
```
Enter your name: Pranay
Enter your age : 20
Hello, Pranay! You are 20 years old.

===== Pre-defined Package: java.util.ArrayList =====
Fruits list : [Mango, Banana, Orange, Apple]
After remove: [Mango, Orange, Apple]
Size        : 3

===== Pre-defined Package: java.util.Arrays =====
Before sort: [5, 3, 8, 1, 9, 2, 7]
After sort : [1, 2, 3, 5, 7, 8, 9]
Search 7   : index 4

===== User-defined Package: mypackage.MathHelper =====
Add(10, 5)      : 15
Subtract(10, 5) : 5
Multiply(4, 6)  : 24
Divide(10, 4)   : 2.5
isPrime(7)      : true
isPrime(10)     : false
```

## Result
The program successfully demonstrates the use of pre-defined and user-defined packages in Java. Pre-defined packages (`java.util`) provide ready-to-use classes like `Scanner`, `ArrayList`, and `Arrays`. The user-defined `mypackage.MathHelper` class is declared with the `package` keyword, compiled with `-d .`, and imported into the main program, showing the complete package creation and usage workflow.

# Practical 4: Keywords — static, final, this in Java

## Aim
To write a program demonstrating the use of keywords `static`, `final`, and `this` in Java.

## Theory

### `static` keyword
- A `static` variable is shared across all instances of the class — only one copy exists in memory.
- A `static` method belongs to the class and can be called without creating an object (`ClassName.methodName()`).
- Common use: counters, utility/helper methods, constants (`Math.PI`).

### `final` keyword
- **`final` variable**: Acts as a constant; its value cannot be changed after assignment.
- **`final` method**: Cannot be overridden by a subclass.
- **`final` class**: Cannot be extended (sub-classed). Example: `java.lang.String` is a final class.

### `this` keyword
- Refers to the **current object** inside an instance method or constructor.
- Used to distinguish between instance variables and method/constructor parameters with the same name.
- `this()` can be used inside a constructor to call **another constructor** in the same class — this is called **constructor chaining**.
- `this` can be returned from a method to return the current object (useful in method chaining/builder pattern).

## Algorithm
1. Define `Counter` class with a `static int count` variable. Increment it in the constructor. Define `static displayCount()` method.
2. Define `MathConstants` class with a `final double PI`. Define `final circleArea(double r)` method.
3. Define `final class Immutable` with a `final int value` field.
4. Define `Employee` class with `name`, `id`, `salary` fields. Show `this` usage in a parameterized constructor and constructor chaining in a default constructor using `this(...)`.
5. In `Runner4.main()`:
   - Create 3 `Counter` objects and call `Counter.displayCount()`.
   - Use `MathConstants` to print PI and compute circle area.
   - Create `Immutable` object and display its value.
   - Create `Employee` objects (parameterized and default), display them, verify `getSelf()`.

## Code

### practical4.java
```java
// Aim: Write a program to demonstrate the use of keywords static, final, and this in Java.

class Counter {
    static int count = 0;
    Counter() { count++; }
    static void displayCount() { System.out.println("Total objects created: " + count); }
}

class MathConstants {
    final double PI = 3.14159265358979;
    final double circleArea(double r) { return PI * r * r; }
}

final class Immutable {
    final int value;
    Immutable(int v) { this.value = v; }
    void show() { System.out.println("Immutable value: " + value); }
}

class Employee {
    String name; int id; double salary;
    Employee() { this("Unknown", 0, 0.0); System.out.println("Default Employee created."); }
    Employee(String name, int id, double salary) { this.name = name; this.id = id; this.salary = salary; }
    void display() { System.out.println("ID: " + this.id + " | Name: " + this.name + " | Salary: " + this.salary); }
    Employee getSelf() { return this; }
}

class Runner4 {
    public static void main(String[] args) {
        System.out.println("===== static Keyword =====");
        new Counter(); new Counter(); new Counter();
        Counter.displayCount();

        System.out.println("\n===== final Keyword =====");
        MathConstants mc = new MathConstants();
        System.out.println("PI = " + mc.PI);
        System.out.printf("Area of circle (r=7): %.4f%n", mc.circleArea(7));
        new Immutable(100).show();

        System.out.println("\n===== this Keyword =====");
        Employee e1 = new Employee("Pranay", 101, 45000.0);
        e1.display();
        Employee e2 = new Employee();
        e2.display();
        System.out.println("getSelf() returns same object: " + (e1.getSelf() == e1));
    }
}
```

## Output
```
===== static Keyword =====
Total objects created: 3

===== final Keyword =====
PI = 3.14159265358979
Area of circle (r=7): 153.9380
Immutable value: 100

===== this Keyword =====
ID: 101 | Name: Pranay | Salary: 45000.0
Default Employee created.
ID: 0 | Name: Unknown | Salary: 0.0
getSelf() returns same object: true
```

## Result
The program successfully demonstrates the use of the `static`, `final`, and `this` keywords in Java. The `static` keyword shows shared class-level state via the counter. The `final` keyword prevents reassignment of constants and shows how to declare non-extendable classes. The `this` keyword resolves naming conflicts and enables constructor chaining.

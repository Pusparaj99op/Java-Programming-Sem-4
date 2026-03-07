# Practical 3: Method Overloading in Java

## Aim
To write a program to demonstrate Method Overloading in Java.

## Theory
**Method Overloading** is a feature of Java that allows a class to have multiple methods with the same name provided their parameter lists are different. The Java compiler selects the correct method at **compile time** based on the method signature (name + parameter types/number/order). This is also called **compile-time polymorphism** or **static polymorphism**.

Overloading can be achieved by:
1. Changing the **number** of parameters (e.g., `area(double r)` vs `area(double l, double b)`)
2. Changing the **data type** of parameters (e.g., `area(double side)` vs `area(int side)`)
3. Changing the **order** of parameter types

Key points:
- Return type alone **cannot** distinguish overloaded methods.
- Overloading improves code readability and reusability.
- It is different from **Method Overriding**, which happens at runtime in subclasses.

## Algorithm
1. Define a `MathUtils` class with overloaded `area()` and `perimeter()` methods:
   - `area(double radius)` — area of a circle.
   - `area(double length, double breadth)` — area of a rectangle.
   - `area(int side)` — area of a square.
   - `perimeter(double a, double b, double c)` — perimeter of a triangle.
   - `perimeter(double length, double breadth)` — perimeter of a rectangle.
   - `perimeter(int side)` — perimeter of a square.
2. Define a `Printer` class with overloaded `print()` methods for `int`, `double`, `String`, and `boolean`.
3. In `Runner3.main()`:
   - Create a `MathUtils` object and call each overloaded method.
   - Create a `Printer` object and call `print()` with each data type.
   - Print formatted results.

## Code

### practical3.java
```java
// Aim: Write a program to demonstrate Method Overloading in Java.

class MathUtils {
    public double area(double radius)           { return Math.PI * radius * radius; }
    public double area(double length, double b) { return length * b; }
    public double area(int side)               { return (double) side * side; }

    public double perimeter(double a, double b, double c) { return a + b + c; }
    public double perimeter(double length, double breadth) { return 2 * (length + breadth); }
    public double perimeter(int side)          { return 4.0 * side; }
}

class Printer {
    public void print(int value)    { System.out.println("Integer value : " + value); }
    public void print(double value) { System.out.println("Double value  : " + value); }
    public void print(String value) { System.out.println("String value  : " + value); }
    public void print(boolean value){ System.out.println("Boolean value : " + value); }
}

class Runner3 {
    public static void main(String[] args) {
        MathUtils m = new MathUtils();

        System.out.println("===== Area Calculations =====");
        System.out.printf("Area of circle (r=5)           : %.4f%n", m.area(5.0));
        System.out.printf("Area of rectangle (4 x 6)      : %.4f%n", m.area(4.0, 6.0));
        System.out.printf("Area of square (side=7)         : %.4f%n", m.area(7));

        System.out.println("\n===== Perimeter Calculations =====");
        System.out.printf("Perimeter of triangle (3,4,5)   : %.4f%n", m.perimeter(3.0, 4.0, 5.0));
        System.out.printf("Perimeter of rectangle (4 x 6)  : %.4f%n", m.perimeter(4.0, 6.0));
        System.out.printf("Perimeter of square (side=7)     : %.4f%n", m.perimeter(7));

        System.out.println("\n===== Printer — Overloading by Type =====");
        Printer p = new Printer();
        p.print(42);
        p.print(3.14);
        p.print("Hello, Pranay!");
        p.print(true);
    }
}
```

## Output
```
===== Area Calculations =====
Area of circle (r=5)           : 78.5398
Area of rectangle (4 x 6)      : 24.0000
Area of square (side=7)         : 49.0000

===== Perimeter Calculations =====
Perimeter of triangle (3,4,5)   : 12.0000
Perimeter of rectangle (4 x 6)  : 20.0000
Perimeter of square (side=7)     : 28.0000

===== Printer — Overloading by Type =====
Integer value : 42
Double value  : 3.14
String value  : Hello, Pranay!
Boolean value : true
```

## Result
The program successfully demonstrates method overloading in Java. The `MathUtils` class shows overloading by changing the number and type of parameters to compute areas and perimeters for different shapes. The `Printer` class shows overloading by parameter type. The correct method is selected at compile time based on the arguments provided.

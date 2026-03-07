# Practical 1: Method Overloading in Java

## Aim
To write a program to demonstrate method overloading in Java.

## Theory
Method overloading is a feature in Java that allows a class to have more than one method with the same name, but with different parameters (different number of parameters or different types of parameters). The compiler determines which method to call based on the method signature (method name and parameter list). This allows for more intuitive and flexible code, as the same method name can be used for similar operations with different inputs.

Key points:
- Method overloading is also known as compile-time polymorphism or static polymorphism.
- Overloaded methods must have the same name but different parameter lists.
- Return type alone is not sufficient to overload methods; parameters must differ.
- Overloading can be done by changing the number of parameters or the types of parameters.

## Algorithm
1. Define a `Product` class with overloaded methods:
   - `multiply(int a, int b)`: Returns the product of two integers.
   - `multiply(int a, int b, int c)`: Returns the product of three integers.
   - `prod(int a, int b, int c)`: Returns the product of three integers.
   - `prod(double a, double b, double c)`: Returns the product of three doubles.

2. Define a `Student` class with overloaded methods:
   - `studentId(String name, int rollNo)`: Prints name and roll number.
   - `studentId(int rollNo, String name)`: Prints roll number and name.

3. In the `Runner` class main method:
   - Create an instance of `Product`.
   - Call the overloaded `multiply` and `prod` methods with different parameters.
   - Print the results.
   - Create an instance of `Student`.
   - Call the overloaded `studentId` methods with different parameter orders.
   - Print the results.

## Code

### Product.java
```java
// Aim: write a program to Demonstrate method overloading in Java

import java.io.*;
class Product
{
    public int multiply(int a, int b) {return a * b;} // Multiplying 2 integer values
    public int multiply(int a, int b, int c) {return a * b * c;} // Multiplying 3 integer values
    public int prod(int a, int b, int c) {return a * b * c;} // Product of 3 integers
    public double prod(double a, double b, double c) {return a * b * c;} // Product of 3 doubles
}

class Student {
    // Student ID with name first
    public void studentId(String name, int rollNo) {System.out.println("Name: " + name + ", Roll-No: " + rollNo);}
    // Student ID with rollNo first
    public void studentId(int rollNo, String name) {System.out.println("Roll-No: " + rollNo + ", Name: " + name);}
}
class Runner {
    public static void main(String[] args) {

        Product p = new Product();
        int prod1 = p.multiply(1, 2);  // Multiply 2 numbers
        System.out.println("Product of two integers: " + prod1);
        int prod2 = p.multiply(1, 2, 3);  // Multiply 3 numbers
        System.out.println("Product of three integers: " + prod2);
        System.out.println("Product of three integers: " + p.prod(1, 2, 3)); // Product of integers
        System.out.println("Product of three doubles: " + p.prod(1.0, 2.0, 3.0)); // Product of doubles
        Student s = new Student();
        s.studentId("Sweta", 1);
        s.studentId(2, "Gudly");
    }}
```

### Calculator.java
```java
import java.util.*;
class Calculator{
    public static void main(String []agrs)
    {   Scanner scanner = new Scanner(System.in);
        System.out.println("Pranay is trying to make a Calculator!!");
        System.out.println("========JAVA CALCULATOR INTERFACE========");
        System.out.println("Enter First Digit");
        int FirstDigit = scanner.nextInt();
        System.out.println("Enter Second Digit");
        int SecondDigit = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please Broh!! Enter YOur Name");
        String Name = scanner.nextLine();
        int  sum = FirstDigit + SecondDigit;
        System.out.println("hellow, " + Name + " the Sum of "  + FirstDigit + " And " + SecondDigit + " is " + sum);
        scanner.close();
    }
}
```

## Output
```
Product of two integers: 2
Product of three integers: 6
Product of three integers: 6
Product of three doubles: 6.0
Name: Sweta, Roll-No: 1
Roll-No: 2, Name: Gudly
```

## Result
The program successfully demonstrates method overloading in Java. Different versions of the `multiply`, `prod`, and `studentId` methods are called based on the number and types of parameters passed. The output shows the correct calculations and formatted printing, confirming that the overloaded methods work as expected.

This practical helps in understanding how method overloading provides flexibility in programming by allowing methods with the same name to handle different types of inputs.

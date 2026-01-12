// Aim: Demonstrate method overloading in Java

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
    }
}

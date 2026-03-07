// Aim: Write a program to demonstrate Constructor and Types of Constructor in Java.

// Types of Constructors in Java:
// 1. Default Constructor
// 2. Parameterized Constructor
// 3. Copy Constructor
// 4. Constructor Overloading

// =================================================================================================
// Class Box demonstrates all types of constructors

class Box {
    double length, breadth, height;

    // 1. Default Constructor
    Box() {
        length = 1.0;
        breadth = 1.0;
        height = 1.0;
        System.out.println("Default Constructor called.");
    }

    // 2. Parameterized Constructor
    Box(double l, double b, double h) {
        length = l;
        breadth = b;
        height = h;
        System.out.println("Parameterized Constructor called.");
    }

    // 3. Copy Constructor (takes another Box object)
    Box(Box other) {
        this.length  = other.length;
        this.breadth = other.breadth;
        this.height  = other.height;
        System.out.println("Copy Constructor called.");
    }

    // 4. Constructor Overloading — single dimension (cube)
    Box(double side) {
        length  = side;
        breadth = side;
        height  = side;
        System.out.println("Cube Constructor called.");
    }

    double volume() {
        return length * breadth * height;
    }

    void display() {
        System.out.println("Length=" + length + ", Breadth=" + breadth + ", Height=" + height);
        System.out.println("Volume = " + volume());
    }
}

// =================================================================================================
// Runner class — entry point

class Runner2 {
    public static void main(String[] args) {

        System.out.println("===== 1. Default Constructor =====");
        Box b1 = new Box();
        b1.display();

        System.out.println("\n===== 2. Parameterized Constructor =====");
        Box b2 = new Box(4.0, 3.0, 2.0);
        b2.display();

        System.out.println("\n===== 3. Copy Constructor =====");
        Box b3 = new Box(b2);
        b3.display();

        System.out.println("\n===== 4. Constructor Overloading — Cube =====");
        Box b4 = new Box(5.0);
        b4.display();
    }
}

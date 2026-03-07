// Aim: Write a program to demonstrate Method Overloading in Java.

// Method Overloading allows multiple methods with the same name
// but different parameter lists (number, type, or order of parameters).

// =================================================================================================
// MathUtils: overloading to calculate area of different shapes

class MathUtils {
    // Area of a circle (1 parameter — radius)
    public double area(double radius) {
        return Math.PI * radius * radius;
    }

    // Area of a rectangle (2 parameters — length and breadth)
    public double area(double length, double breadth) {
        return length * breadth;
    }

    // Area of a square (1 int parameter — side)
    public double area(int side) {
        return (double) side * side;
    }

    // Perimeter of a triangle (3 sides)
    public double perimeter(double a, double b, double c) {
        return a + b + c;
    }

    // Perimeter of a rectangle (length + breadth)
    public double perimeter(double length, double breadth) {
        return 2 * (length + breadth);
    }

    // Perimeter of a square (single side)
    public double perimeter(int side) {
        return 4.0 * side;
    }
}

// =================================================================================================
// Printer: overloading by parameter type

class Printer {
    public void print(int value)    { System.out.println("Integer value : " + value); }
    public void print(double value) { System.out.println("Double value  : " + value); }
    public void print(String value) { System.out.println("String value  : " + value); }
    public void print(boolean value){ System.out.println("Boolean value : " + value); }
}

// =================================================================================================
// Runner class — entry point

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

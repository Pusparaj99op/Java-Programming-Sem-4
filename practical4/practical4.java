// Aim: Write a program to demonstrate the use of keywords static, final, and this in Java.

// =================================================================================================
// 1. static keyword — shared variable and shared method

class Counter {
    static int count = 0;  // static variable: shared by all objects

    Counter() {
        count++;  // increment on every object creation
    }

    static void displayCount() {  // static method: called without object
        System.out.println("Total objects created: " + count);
    }
}

// =================================================================================================
// 2. final keyword — constant variable, non-overridable method, non-extendable class

class MathConstants {
    final double PI = 3.14159265358979;  // final variable — cannot be reassigned

    final double circleArea(double r) {  // final method — cannot be overridden
        return PI * r * r;
    }
}

// final class — cannot be extended
final class Immutable {
    final int value;

    Immutable(int v) {
        this.value = v;
    }

    void show() {
        System.out.println("Immutable value: " + value);
    }
}

// =================================================================================================
// 3. this keyword — refers to current object, chains constructors

class Employee {
    String name;
    int    id;
    double salary;

    // Constructor chaining using this()
    Employee() {
        this("Unknown", 0, 0.0);  // calls the 3-argument constructor
        System.out.println("Default Employee created.");
    }

    Employee(String name, int id, double salary) {
        this.name   = name;    // this.name refers to field, name refers to parameter
        this.id     = id;
        this.salary = salary;
    }

    void display() {
        System.out.println("ID: " + this.id + " | Name: " + this.name + " | Salary: " + this.salary);
    }

    // Returns current object using this
    Employee getSelf() {
        return this;
    }
}

// =================================================================================================
// Runner class — entry point

class Runner4 {
    public static void main(String[] args) {

        // ---- static demonstration ----
        System.out.println("===== static Keyword =====");
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();
        Counter.displayCount();  // called on class, not object

        // ---- final demonstration ----
        System.out.println("\n===== final Keyword =====");
        MathConstants mc = new MathConstants();
        System.out.println("PI = " + mc.PI);
        System.out.printf("Area of circle (r=7): %.4f%n", mc.circleArea(7));

        Immutable im = new Immutable(100);
        im.show();

        // ---- this demonstration ----
        System.out.println("\n===== this Keyword =====");
        Employee e1 = new Employee("Pranay", 101, 45000.0);
        e1.display();

        Employee e2 = new Employee();  // triggers constructor chaining
        e2.display();

        System.out.println("getSelf() returns same object: " + (e1.getSelf() == e1));
    }
}

# Practical 2: Constructor & Types of Constructor in Java

## Aim
To write a program to demonstrate Constructor and Types of Constructor in Java.

## Theory
A **constructor** in Java is a special method that is automatically called when an object is created. It has the same name as the class and no return type.

**Types of Constructors:**

1. **Default Constructor** — Takes no parameters; Java provides one automatically if none is defined. Used to set default initial values.
2. **Parameterized Constructor** — Takes one or more parameters to initialise object fields with specific values at creation time.
3. **Copy Constructor** — Takes an object of the same class as a parameter and copies its field values. Java does not provide a copy constructor automatically; it must be written explicitly.
4. **Constructor Overloading** — Defining multiple constructors in the same class with different parameter lists, allowing objects to be created in different ways.

Key points:
- A constructor is invoked with the `new` keyword.
- Constructors cannot be inherited or overridden.
- The `this()` call can be used inside one constructor to invoke another constructor in the same class (constructor chaining).

## Algorithm
1. Define a `Box` class with three fields: `length`, `breadth`, `height`.
2. Implement a **Default Constructor** that sets all dimensions to 1.0.
3. Implement a **Parameterized Constructor** that accepts `l`, `b`, `h` and assigns them.
4. Implement a **Copy Constructor** that accepts another `Box` object and copies its fields.
5. Implement a **Cube Constructor** (overloading) that accepts a single `side` value and sets all three dimensions to it.
6. Implement a `volume()` method returning `length * breadth * height`.
7. Implement a `display()` method printing all dimensions and volume.
8. In `Runner2.main()`:
   - Create `b1` using default constructor and display.
   - Create `b2` using parameterized constructor and display.
   - Create `b3` as a copy of `b2` and display.
   - Create `b4` using the cube constructor and display.

## Code

### practical2.java
```java
// Aim: Write a program to demonstrate Constructor and Types of Constructor in Java.

class Box {
    double length, breadth, height;

    // 1. Default Constructor
    Box() {
        length = 1.0; breadth = 1.0; height = 1.0;
        System.out.println("Default Constructor called.");
    }

    // 2. Parameterized Constructor
    Box(double l, double b, double h) {
        length = l; breadth = b; height = h;
        System.out.println("Parameterized Constructor called.");
    }

    // 3. Copy Constructor
    Box(Box other) {
        this.length = other.length; this.breadth = other.breadth; this.height = other.height;
        System.out.println("Copy Constructor called.");
    }

    // 4. Constructor Overloading — Cube
    Box(double side) {
        length = side; breadth = side; height = side;
        System.out.println("Cube Constructor called.");
    }

    double volume() { return length * breadth * height; }

    void display() {
        System.out.println("Length=" + length + ", Breadth=" + breadth + ", Height=" + height);
        System.out.println("Volume = " + volume());
    }
}

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
```

## Output
```
===== 1. Default Constructor =====
Default Constructor called.
Length=1.0, Breadth=1.0, Height=1.0
Volume = 1.0

===== 2. Parameterized Constructor =====
Parameterized Constructor called.
Length=4.0, Breadth=3.0, Height=2.0
Volume = 24.0

===== 3. Copy Constructor =====
Copy Constructor called.
Length=4.0, Breadth=3.0, Height=2.0
Volume = 24.0

===== 4. Constructor Overloading — Cube =====
Cube Constructor called.
Length=5.0, Breadth=5.0, Height=5.0
Volume = 125.0
```

## Result
The program successfully demonstrates all four types of constructors in Java. The `Box` class uses a default constructor, a parameterized constructor, a copy constructor, and constructor overloading (cube). Each constructor is triggered through a different `new Box(...)` call, confirming Java's ability to support multiple constructors through overloading.

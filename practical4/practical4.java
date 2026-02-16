// Aim: Write a program to demonstrate the inheritance and it's types in java.

//  Types of Inheritance in Java:
//  1. Single Inheritance
//  2. Multilevel Inheritance
//  3. Hierarchical Inheritance
//  4. Multiple Inheritance




// =================================================================================================
// 1. Single Inheritance
class Vehicle {
    void display() {
        system.out.println("====================Start Single Inheritance================================");
        System.out.println("This is a vehicle.");
    }
}
class Car extends Vehicle {
    void display() {
        System.out.println("This is a car.");
    }
}
Class SingleInheritance {
    public static void main(String[] args) {
        Car car = new Car();
        car.display(); // This will call the display method of Car class
        system.out.println("====================End Single Inheritance================================");
    }
}


// =================================================================================================
// 2. Multilevel Inheritance
class Animal {
    void eat() {
        System.out.println("====================Start Multilevel Inheritance================================");
        System.out.println("This animal eats animal.");
    }
}
class Lion extends Animal {
    void eat() {
        System.out.println("This lion eats meat.");
    }
}
class Deer extends Lion {
    void eat() {
        System.out.println("This deer eats grass.");
    }
}

Class MultilevelInheritance{
public static void main(String[] args) {
    Deer deer = new Deer();
    deer.eat(); // This will call the eat method of Deer class
    system.out.println("====================End Multilevel Inheritance================================");
}

// ================================================================================================
// 3. Hierarchical Inheritance
class Shape {
    void display() {
        System.out.println("====================Start Hierarchical Inheritance================================");
        System.out.println("This is a shape.");
    }
}
class Circle extends Shape {
    void display() {
        System.out.println("This is a circle.");
    }
}
class Square extends Shape {
    void display() {
        System.out.println("This is a square.");
    }
}
class HierarchicalInheritance {
    public static void main(String[] args) {
        Circle circle = new Circle();
        Square square = new Square();
        circle.display(); // This will call the display method of Circle class
        square.display(); // This will call the display method of Square class
        system.out.println("====================End Hierarchical Inheritance================================");
    }
}

// =================================================================================================
// 4. Multiple Inheritance (using interfaces)
interface LandVehicle {
    void displayA();
    System.out.println("====================Start Multiple Inheritance================================");
    System.out.println("This is interface Of Landvehicle.");
}
interface WaterVehicle {
    void displayB();
        System.out.println("This is interface Of WaterVehicle.");
}
class MultipleInheritance implements LandVehicle, WaterVehicle {
        public static void main(String[] args) {
        MultipleInheritance mi = new MultipleInheritance();
        mi.displayA(); // This will call the displayA method of MultipleInheritance class
        mi.displayB(); // This will call the displayB method of MultipleInheritance class
        system.out.println("====================Ends Multiple Inheritance================================");
    }
}
// Aim: Write a program to demonstrate Inheritance and its types in Java.

// Types of Inheritance in Java:
// 1. Single Inheritance       — one class extends one class
// 2. Multilevel Inheritance   — chain of classes (A -> B -> C)
// 3. Hierarchical Inheritance — multiple classes extend one class
// 4. Multiple Inheritance     — achieved via interfaces (Java does not support via classes)

// =================================================================================================
// 1. Single Inheritance

class Person {
    String name;
    int    age;

    Person(String n, int a) { name = n; age = a; }

    void introduce() {
        System.out.println("Hi, I am " + name + " and I am " + age + " years old.");
    }
}

class Student5 extends Person {
    int rollNo;

    Student5(String n, int a, int r) {
        super(n, a);   // call parent constructor
        rollNo = r;
    }

    void introduce() {
        super.introduce();
        System.out.println("My Roll No is: " + rollNo);
    }
}

class SingleInheritanceDemo {
    public static void main(String[] args) {
        System.out.println("===== Single Inheritance =====");
        Student5 s = new Student5("Pranay", 20, 42);
        s.introduce();
    }
}

// =================================================================================================
// 2. Multilevel Inheritance

class LivingBeing {
    void breathe() { System.out.println("Living beings breathe."); }
}

class Mammal extends LivingBeing {
    void feedMilk() { System.out.println("Mammals feed milk to offspring."); }
}

class Dog extends Mammal {
    void bark() { System.out.println("Dog barks: Woof!"); }
}

class MultilevelInheritanceDemo {
    public static void main(String[] args) {
        System.out.println("===== Multilevel Inheritance =====");
        Dog d = new Dog();
        d.breathe();    // from LivingBeing
        d.feedMilk();   // from Mammal
        d.bark();       // from Dog
    }
}

// =================================================================================================
// 3. Hierarchical Inheritance

class Shape5 {
    double area() { return 0; }
    void display() { System.out.println("Shape area: " + area()); }
}

class Circle5 extends Shape5 {
    double radius;
    Circle5(double r) { radius = r; }

    @Override
    double area() { return Math.PI * radius * radius; }
}

class Rectangle5 extends Shape5 {
    double length, breadth;
    Rectangle5(double l, double b) { length = l; breadth = b; }

    @Override
    double area() { return length * breadth; }
}

class Triangle5 extends Shape5 {
    double base, height;
    Triangle5(double b, double h) { base = b; height = h; }

    @Override
    double area() { return 0.5 * base * height; }
}

class HierarchicalInheritanceDemo {
    public static void main(String[] args) {
        System.out.println("===== Hierarchical Inheritance =====");
        Circle5    c = new Circle5(5);
        Rectangle5 r = new Rectangle5(4, 6);
        Triangle5  t = new Triangle5(3, 8);

        System.out.printf("Circle    area: %.4f%n", c.area());
        System.out.printf("Rectangle area: %.4f%n", r.area());
        System.out.printf("Triangle  area: %.4f%n", t.area());
    }
}

// =================================================================================================
// 4. Multiple Inheritance (via interfaces)

interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck implements Flyable, Swimmable {
    @Override
    public void fly()  { System.out.println("Duck is flying."); }

    @Override
    public void swim() { System.out.println("Duck is swimming."); }

    void quack() { System.out.println("Duck says: Quack!"); }
}

class MultipleInheritanceDemo {
    public static void main(String[] args) {
        System.out.println("===== Multiple Inheritance (interfaces) =====");
        Duck duck = new Duck();
        duck.fly();
        duck.swim();
        duck.quack();
    }
}

// =================================================================================================
// Runner — demonstrates all types together

class Runner5 {
    public static void main(String[] args) {

        System.out.println("===== 1. Single Inheritance =====");
        Student5 s = new Student5("Pranay", 20, 42);
        s.introduce();

        System.out.println("\n===== 2. Multilevel Inheritance =====");
        Dog d = new Dog();
        d.breathe();
        d.feedMilk();
        d.bark();

        System.out.println("\n===== 3. Hierarchical Inheritance =====");
        Circle5    c = new Circle5(5);
        Rectangle5 r = new Rectangle5(4, 6);
        Triangle5  t = new Triangle5(3, 8);
        System.out.printf("Circle    area: %.4f%n", c.area());
        System.out.printf("Rectangle area: %.4f%n", r.area());
        System.out.printf("Triangle  area: %.4f%n", t.area());

        System.out.println("\n===== 4. Multiple Inheritance (interfaces) =====");
        Duck duck = new Duck();
        duck.fly();
        duck.swim();
        duck.quack();
    }
}

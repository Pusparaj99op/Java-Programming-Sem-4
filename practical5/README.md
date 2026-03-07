# Practical 5: Inheritance & Types in Java

## Aim
To write a program to demonstrate Inheritance and its types in Java.

## Theory
**Inheritance** is a fundamental concept in Object-Oriented Programming that allows a class (child/subclass) to acquire the properties and behaviours of another class (parent/superclass). It promotes **code reusability** and **hierarchical classification**.

**Types of Inheritance:**

1. **Single Inheritance**: A class inherits from exactly one parent class. `class B extends A`
2. **Multilevel Inheritance**: A class inherits from another class which itself inherits from yet another. `A → B → C`
3. **Hierarchical Inheritance**: Multiple classes inherit from a single parent. `A → B`, `A → C`, `A → D`
4. **Multiple Inheritance**: A class inherits from more than one class. Java does **not** support this with classes (to avoid the diamond problem), but achieves it through **interfaces**.

Key points:
- `extends` keyword is used for class inheritance.
- `implements` keyword is used for interface inheritance.
- `super` keyword is used to call the parent class constructor or method.
- `@Override` annotation explicitly marks a method as overriding a parent method.

## Algorithm
1. **Single Inheritance**: Define `Person` with `name`, `age`, and `introduce()`. Define `Student5 extends Person` with an additional `rollNo` field.
2. **Multilevel Inheritance**: Define `LivingBeing` → `Mammal` → `Dog`. Each level adds a new behaviour.
3. **Hierarchical Inheritance**: Define `Shape5` with `area()`. Create `Circle5`, `Rectangle5`, `Triangle5` each extending `Shape5` and overriding `area()`.
4. **Multiple Inheritance**: Define `Flyable` and `Swimmable` interfaces. Class `Duck` implements both.
5. In `Runner5.main()`: demonstrate all four types sequentially.

## Code

### practical5.java
```java
// Aim: Write a program to demonstrate Inheritance and its types in Java.

// 1. Single Inheritance
class Person {
    String name; int age;
    Person(String n, int a) { name = n; age = a; }
    void introduce() { System.out.println("Hi, I am " + name + " and I am " + age + " years old."); }
}
class Student5 extends Person {
    int rollNo;
    Student5(String n, int a, int r) { super(n, a); rollNo = r; }
    void introduce() { super.introduce(); System.out.println("My Roll No is: " + rollNo); }
}

// 2. Multilevel Inheritance
class LivingBeing { void breathe() { System.out.println("Living beings breathe."); } }
class Mammal extends LivingBeing { void feedMilk() { System.out.println("Mammals feed milk to offspring."); } }
class Dog extends Mammal { void bark() { System.out.println("Dog barks: Woof!"); } }

// 3. Hierarchical Inheritance
class Shape5 { double area() { return 0; } }
class Circle5 extends Shape5 {
    double radius; Circle5(double r) { radius = r; }
    @Override double area() { return Math.PI * radius * radius; }
}
class Rectangle5 extends Shape5 {
    double length, breadth; Rectangle5(double l, double b) { length = l; breadth = b; }
    @Override double area() { return length * breadth; }
}
class Triangle5 extends Shape5 {
    double base, height; Triangle5(double b, double h) { base = b; height = h; }
    @Override double area() { return 0.5 * base * height; }
}

// 4. Multiple Inheritance (interfaces)
interface Flyable { void fly(); }
interface Swimmable { void swim(); }
class Duck implements Flyable, Swimmable {
    public void fly()  { System.out.println("Duck is flying."); }
    public void swim() { System.out.println("Duck is swimming."); }
    void quack() { System.out.println("Duck says: Quack!"); }
}

class Runner5 {
    public static void main(String[] args) {
        System.out.println("===== 1. Single Inheritance =====");
        new Student5("Pranay", 20, 42).introduce();

        System.out.println("\n===== 2. Multilevel Inheritance =====");
        Dog d = new Dog(); d.breathe(); d.feedMilk(); d.bark();

        System.out.println("\n===== 3. Hierarchical Inheritance =====");
        System.out.printf("Circle    area: %.4f%n", new Circle5(5).area());
        System.out.printf("Rectangle area: %.4f%n", new Rectangle5(4, 6).area());
        System.out.printf("Triangle  area: %.4f%n", new Triangle5(3, 8).area());

        System.out.println("\n===== 4. Multiple Inheritance (interfaces) =====");
        Duck duck = new Duck(); duck.fly(); duck.swim(); duck.quack();
    }
}
```

## Output
```
===== 1. Single Inheritance =====
Hi, I am Pranay and I am 20 years old.
My Roll No is: 42

===== 2. Multilevel Inheritance =====
Living beings breathe.
Mammals feed milk to offspring.
Dog barks: Woof!

===== 3. Hierarchical Inheritance =====
Circle    area: 78.5398
Rectangle area: 24.0000
Triangle  area: 12.0000

===== 4. Multiple Inheritance (interfaces) =====
Duck is flying.
Duck is swimming.
Duck says: Quack!
```

## Result
The program successfully demonstrates all four types of inheritance in Java. Single inheritance shows parent–child class relationships. Multilevel inheritance shows a chain of three levels. Hierarchical inheritance shows multiple subclasses sharing a common parent. Multiple inheritance via interfaces shows a class implementing behaviours from two different interfaces simultaneously.

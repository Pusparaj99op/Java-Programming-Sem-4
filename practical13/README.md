# Practical 13: Implementation of Class Framework (Iterator Pattern) in Java

## Aim
To write a program to implement the class framework using the Iterator Pattern in Java.

## Theory
The **Iterator Pattern** is a behavioral design pattern that provides a standard way to traverse the elements of a collection without exposing the internal structure (array, linked list, tree, etc.) of the collection.

**Key components:**
1. **Iterator interface** — declares `hasNext()` and `next()` methods.
2. **ConcreteIterator** — implements the Iterator interface; maintains a cursor.
3. **Aggregate (Collection)** — defines a method to return an Iterator.
4. **ConcreteAggregate** — the actual collection; creates and returns a ConcreteIterator.

**Java built-in support:**
- `java.util.Iterator<E>` — forward-only iterator: `hasNext()`, `next()`, `remove()`.
- `java.util.ListIterator<E>` — bidirectional: adds `hasPrevious()`, `previous()`, `add()`, `set()`.
- `Iterable<E>` interface — any class implementing it can be used in a `for-each` loop.

**Why Iterator Pattern?**
- Decouples the traversal algorithm from the collection.
- Supports multiple simultaneous traversals.
- Provides a uniform interface for traversing different collection types.

## Algorithm
1. Define `MyIterator<T>` interface with `hasNext()` and `next()` methods.
2. Define `NumberCollection` with an `int[]` array and `add()` method. Create inner class `NumberIterator` implementing `MyIterator<Integer>`. Provide factory method `iterator()`.
3. Define `StringCollection` similarly; use an anonymous inner class for the iterator.
4. In `Runner13.main()`:
   - Populate `NumberCollection`, traverse with custom iterator, compute sum.
   - Populate `StringCollection`, traverse with custom iterator.
   - Use Java built-in `Iterator` on `ArrayList`; demonstrate safe `remove()`.
   - Use `ListIterator` for forward and backward traversal.

## Code

### practical13.java
```java
// Aim: Write a program to implement the class framework (Iterator Pattern) in Java.

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

interface MyIterator<T> {
    boolean hasNext();
    T next();
}

class NumberCollection {
    private int[] data; private int size;
    NumberCollection(int cap) { data = new int[cap]; size = 0; }
    void add(int v) { if (size < data.length) data[size++] = v; }

    class NumberIterator implements MyIterator<Integer> {
        private int index = 0;
        public boolean hasNext() { return index < size; }
        public Integer next() { return data[index++]; }
    }
    MyIterator<Integer> iterator() { return new NumberIterator(); }
}

class Runner13 {
    public static void main(String[] args) {
        NumberCollection nc = new NumberCollection(5);
        for (int i = 10; i <= 50; i += 10) nc.add(i);
        MyIterator<Integer> it = nc.iterator();
        int sum = 0;
        while (it.hasNext()) { int v = it.next(); System.out.print(v + " "); sum += v; }
        System.out.println("\nSum: " + sum);

        // Java built-in Iterator
        ArrayList<String> list = new ArrayList<>();
        list.add("Mango"); list.add("Banana"); list.add("Orange");
        Iterator<String> jit = list.iterator();
        while (jit.hasNext()) { String s = jit.next(); if (s.equals("Banana")) jit.remove(); }
        System.out.println("After remove: " + list);

        // ListIterator — bidirectional
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 5; i++) nums.add(i * 10);
        ListIterator<Integer> li = nums.listIterator();
        System.out.print("Forward : "); while (li.hasNext()) System.out.print(li.next() + " ");
        System.out.print("\nBackward: "); while (li.hasPrevious()) System.out.print(li.previous() + " ");
        System.out.println();
    }
}
```

## Output
```
===== Custom Iterator: NumberCollection =====
Elements: 10 20 30 40 50
Sum: 150

===== Custom Iterator: StringCollection =====
Names: Pranay Sita Raju Neha

===== Java Built-in Iterator: ArrayList =====
Forward: Mango Banana Orange Apple Grape
After removing Banana: [Mango, Orange, Apple, Grape]

===== ListIterator: Bidirectional =====
Forward : 10 20 30 40 50
Backward: 50 40 30 20 10
```

## Result
The program successfully implements the Iterator Pattern in Java. A custom `MyIterator<T>` interface is defined and implemented via inner classes in `NumberCollection` and `StringCollection`, demonstrating the pattern's core structure. The Java built-in `Iterator` and `ListIterator` are also demonstrated, showing safe element removal during iteration and bidirectional traversal, confirming a complete understanding of the class framework.

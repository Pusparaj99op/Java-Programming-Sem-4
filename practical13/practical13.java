// Aim: Write a program to implement the class framework (Iterator Pattern) in Java.

// The Iterator Pattern provides a way to access elements of a collection
// sequentially without exposing the underlying implementation.

// Topics covered:
// 1. Custom Iterator interface and implementation
// 2. Custom collection class using the Iterator pattern
// 3. Java built-in Iterator on ArrayList
// 4. ListIterator for bidirectional traversal

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

// =================================================================================================
// 1. Custom Iterator interface (mirrors java.util.Iterator)

interface MyIterator<T> {
    boolean hasNext();  // true if more elements remain
    T next();           // returns next element
}

// =================================================================================================
// 2. NumberCollection — a custom collection of integers

class NumberCollection {
    private int[] data;
    private int   size;

    NumberCollection(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    void add(int value) {
        if (size < data.length) {
            data[size++] = value;
        } else {
            System.out.println("Collection is full. Cannot add " + value);
        }
    }

    int size() { return size; }

    // Inner class: NumberIterator implements MyIterator
    class NumberIterator implements MyIterator<Integer> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new RuntimeException("No more elements.");
            }
            return data[index++];
        }
    }

    // Factory method to get an iterator
    MyIterator<Integer> iterator() {
        return new NumberIterator();
    }
}

// =================================================================================================
// 3. StringCollection — demonstrates iterator with String type

class StringCollection {
    private String[] items;
    private int      size;

    StringCollection(int capacity) {
        items = new String[capacity];
        size  = 0;
    }

    void add(String s) {
        if (size < items.length) items[size++] = s;
    }

    // Anonymous inner class iterator — returns MyIterator directly
    MyIterator<String> iterator() {
        return new MyIterator<String>() {
            private int idx = 0;

            @Override
            public boolean hasNext() { return idx < size; }

            @Override
            public String next() {
                if (!hasNext()) throw new RuntimeException("No more elements.");
                return items[idx++];
            }
        };
    }
}

// =================================================================================================
// Runner class — entry point

class Runner13 {
    public static void main(String[] args) {

        // ---- Custom Iterator — NumberCollection ----
        System.out.println("===== Custom Iterator: NumberCollection =====");
        NumberCollection nc = new NumberCollection(5);
        nc.add(10); nc.add(20); nc.add(30); nc.add(40); nc.add(50);

        MyIterator<Integer> it = nc.iterator();
        System.out.print("Elements: ");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // Sum using iterator
        it = nc.iterator();  // reset — get fresh iterator
        int sum = 0;
        while (it.hasNext()) sum += it.next();
        System.out.println("Sum: " + sum);

        // ---- Custom Iterator — StringCollection ----
        System.out.println("\n===== Custom Iterator: StringCollection =====");
        StringCollection sc = new StringCollection(4);
        sc.add("Pranay"); sc.add("Sita"); sc.add("Raju"); sc.add("Neha");

        MyIterator<String> sit = sc.iterator();
        System.out.print("Names: ");
        while (sit.hasNext()) {
            System.out.print(sit.next() + " ");
        }
        System.out.println();

        // ---- Java built-in Iterator on ArrayList ----
        System.out.println("\n===== Java Built-in Iterator: ArrayList =====");
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Mango"); fruits.add("Banana"); fruits.add("Orange"); fruits.add("Apple"); fruits.add("Grape");

        Iterator<String> javaIt = fruits.iterator();
        System.out.print("Forward: ");
        while (javaIt.hasNext()) {
            System.out.print(javaIt.next() + " ");
        }
        System.out.println();

        // Remove using iterator (safe removal during iteration)
        Iterator<String> removeIt = fruits.iterator();
        while (removeIt.hasNext()) {
            String fruit = removeIt.next();
            if (fruit.equals("Banana")) {
                removeIt.remove();  // safe remove
            }
        }
        System.out.println("After removing Banana: " + fruits);

        // ---- ListIterator — bidirectional traversal ----
        System.out.println("\n===== ListIterator: Bidirectional =====");
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 5; i++) nums.add(i * 10);

        ListIterator<Integer> li = nums.listIterator();
        System.out.print("Forward : ");
        while (li.hasNext()) System.out.print(li.next() + " ");
        System.out.println();

        System.out.print("Backward: ");
        while (li.hasPrevious()) System.out.print(li.previous() + " ");
        System.out.println();
    }
}

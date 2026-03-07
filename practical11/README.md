# Practical 11: String Class and its Methods in Java

## Aim
To write a program on String Class and its methods in Java.

## Theory
In Java, `String` is a class in `java.lang` package (auto-imported). Strings are **immutable** — once created, their value cannot be changed. Any modification creates a new `String` object.

**Key String methods:**

| Method | Description |
|--------|-------------|
| `length()` | Number of characters |
| `charAt(i)` | Character at index i |
| `substring(i)` / `substring(i,j)` | Substring from index i to end / i to j-1 |
| `indexOf(c)` / `lastIndexOf(c)` | First/last occurrence index |
| `toUpperCase()` / `toLowerCase()` | Case conversion |
| `trim()` | Removes leading and trailing spaces |
| `replace(old,new)` | Replaces all occurrences |
| `contains(s)` | Checks if substring exists |
| `startsWith(s)` / `endsWith(s)` | Prefix/suffix check |
| `split(regex)` | Splits into array by delimiter |
| `concat(s)` | Appends string |
| `equals(s)` / `equalsIgnoreCase(s)` | Content comparison |
| `compareTo(s)` | Lexicographic comparison |
| `String.valueOf(x)` | Converts any type to String |

**StringBuffer vs StringBuilder:**

| Feature | StringBuffer | StringBuilder |
|---------|-------------|---------------|
| Mutability | Mutable | Mutable |
| Thread Safety | Synchronized (thread-safe) | Not synchronized |
| Speed | Slower | Faster |
| Use case | Multi-threaded | Single-threaded |

## Algorithm
1. Demonstrate core `String` methods: `trim`, `charAt`, `indexOf`, `substring`, `toUpperCase`, `toLowerCase`, `replace`, `contains`, `split`, `concat`, `valueOf`.
2. Show string comparison using `==`, `equals()`, `equalsIgnoreCase()`, `compareTo()`.
3. Use `StringBuffer` to build a string with `append`, `insert`, `replace`, `delete`, `reverse`.
4. Use `StringBuilder` to build and modify a string.
5. Run all demos from `Runner11.main()`.

## Code

### practical11.java
```java
// Aim: Write a program on String Class and its methods in Java.

class StringMethodsDemo {
    static void demo() {
        String s = "  Hello, Pranay Gajbhiye!  ";
        System.out.println("trim(): [" + s.trim() + "]");
        String t = s.trim();
        System.out.println("charAt(7): " + t.charAt(7));
        System.out.println("indexOf('G'): " + t.indexOf('G'));
        System.out.println("substring(7): " + t.substring(7));
        System.out.println("toUpperCase: " + t.toUpperCase());
        System.out.println("replace 'a'->'@': " + t.replace('a','@'));
        System.out.println("contains 'Pranay': " + t.contains("Pranay"));
        String[] parts = "Mango,Banana,Orange".split(",");
        for (String p : parts) System.out.println("  " + p);
    }
}

class StringComparisonDemo {
    static void demo() {
        String s1 = "java", s3 = new String("java"), s4 = "JAVA";
        System.out.println("s1==s3: " + (s1==s3));
        System.out.println("equals: " + s1.equals(s3));
        System.out.println("equalsIgnoreCase: " + s1.equalsIgnoreCase(s4));
        System.out.println("compareTo: " + s1.compareTo(s4));
    }
}

class StringBufferDemo {
    static void demo() {
        StringBuffer sb = new StringBuffer("Hello");
        sb.append(" World"); System.out.println("append: " + sb);
        sb.insert(5,","); System.out.println("insert: " + sb);
        sb.delete(5,6); System.out.println("delete: " + sb);
        sb.reverse(); System.out.println("reverse: " + sb);
    }
}

class Runner11 {
    public static void main(String[] args) {
        System.out.println("===== String Methods ====="); StringMethodsDemo.demo();
        System.out.println("\n===== Comparison =====");    StringComparisonDemo.demo();
        System.out.println("\n===== StringBuffer =====");  StringBufferDemo.demo();
    }
}
```

## Output
```
===== String Methods =====
trim(): [Hello, Pranay Gajbhiye!]
charAt(7): P
indexOf('G'): 14
substring(7): Pranay Gajbhiye!
toUpperCase: HELLO, PRANAY GAJBHIYE!
replace 'a'->'@': Hello, Pr@n@y G@jbhiye!
contains 'Pranay': true
  Mango
  Banana
  Orange

===== Comparison =====
s1==s3: false
equals: true
equalsIgnoreCase: true
compareTo: 32

===== StringBuffer =====
append: Hello World
insert: Hello, World
delete: Hello World
reverse: dlroW olleH

===== StringBuilder =====
Built string: K.D.K. College | B.Tech CSE | Pranay Gajbhiye
```

## Result
The program successfully demonstrates the String class and its methods in Java. Core manipulation methods (`trim`, `charAt`, `substring`, `replace`, `split`) are shown. Comparison methods clearly distinguish between `==` (reference) and `equals()` (content). `StringBuffer` demonstrates mutable string operations with thread safety, and `StringBuilder` shows the faster single-threaded alternative.

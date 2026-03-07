# Practical 6: Exception Handling in Java

## Aim
To write a program to implement the concepts of Exception Handling in Java.

## Theory
An **exception** is an unwanted or unexpected event that disrupts the normal flow of a program at runtime. Java provides a robust exception handling mechanism using the following keywords:

| Keyword | Purpose |
|---------|---------|
| `try` | Encloses code that might throw an exception |
| `catch` | Handles the thrown exception |
| `finally` | Always executes, whether or not an exception occurred |
| `throw` | Manually throws an exception |
| `throws` | Declares that a method may throw a checked exception |

**Types of Exceptions:**
- **Checked Exceptions**: Detected at compile time; must be handled or declared (e.g., `IOException`).
- **Unchecked Exceptions**: Detected at runtime; subclasses of `RuntimeException` (e.g., `ArithmeticException`, `NullPointerException`).

The `finally` block is used for cleanup code (closing files, connections, etc.) and always runs unless `System.exit()` is called.

## Algorithm
1. **Basic try-catch-finally**: Divide by zero, catch `ArithmeticException`, run `finally`.
2. **Multiple catch blocks**:
   - Access invalid array index → `ArrayIndexOutOfBoundsException`
   - Parse invalid string as int → `NumberFormatException`
   - Call method on null reference → `NullPointerException`
3. **Checked Exception**: Method `readFile()` declared with `throws IOException`; caller handles it.
4. **Nested try-catch**: Outer try contains inner try-catch; outer catch handles a different exception.
5. In `Runner6.main()`: call each demo method sequentially.

## Code

### practical6.java
```java
// Aim: Write a program to implement the concepts of Exception Handling in Java.

import java.io.*;

class BasicException {
    static void demo() {
        try { int r = 10 / 0; }
        catch (ArithmeticException e) { System.out.println("Caught ArithmeticException: " + e.getMessage()); }
        finally { System.out.println("finally block always executes."); }
    }
}

class MultipleCatch {
    static void demo() {
        try { int[] a = {1,2,3}; System.out.println(a[5]); }
        catch (ArrayIndexOutOfBoundsException e) { System.out.println("Caught AIOOBE: " + e.getMessage()); }
        try { Integer.parseInt("abc"); }
        catch (NumberFormatException e) { System.out.println("Caught NFE: " + e.getMessage()); }
        try { String s = null; s.length(); }
        catch (NullPointerException e) { System.out.println("Caught NPE: " + e.getMessage()); }
    }
}

class FileReader6 {
    static void readFile(String f) throws IOException { throw new IOException("File not found: " + f); }
    static void demo() {
        try { readFile("data.txt"); }
        catch (IOException e) { System.out.println("Caught IOException: " + e.getMessage()); }
    }
}

class NestedTryCatch {
    static void demo() {
        try {
            try { int[] a = new int[2]; a[5] = 10; }
            catch (ArrayIndexOutOfBoundsException e) { System.out.println("Inner catch: " + e.getMessage()); }
            int r = 10 / 0;
        } catch (ArithmeticException e) { System.out.println("Outer catch: " + e.getMessage()); }
        finally { System.out.println("Outer finally block."); }
    }
}

class Runner6 {
    public static void main(String[] args) {
        System.out.println("--- try-catch-finally ---");   BasicException.demo();
        System.out.println("\n--- Multiple catch ---");     MultipleCatch.demo();
        System.out.println("\n--- Checked Exception ---");  FileReader6.demo();
        System.out.println("\n--- Nested try-catch ---");   NestedTryCatch.demo();
    }
}
```

## Output
```
--- try-catch-finally ---
Caught ArithmeticException: / by zero
finally block always executes.

--- Multiple catch ---
Caught AIOOBE: Index 5 out of bounds for length 3
Caught NFE: For input string: "abc"
Caught NPE: Cannot invoke "String.length()" because "s" is null

--- Checked Exception ---
Caught IOException: File not found: data.txt

--- Nested try-catch ---
Inner catch: Index 5 out of bounds for length 2
Outer catch: / by zero
Outer finally block.
```

## Result
The program successfully demonstrates the exception handling mechanism in Java. The `try-catch-finally` structure gracefully handles runtime errors. Multiple catch blocks handle different exception types individually. Checked exceptions are handled using the `throws` declaration. Nested try-catch handles exceptions at different levels of execution.

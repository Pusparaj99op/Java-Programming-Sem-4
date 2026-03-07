# Practical 7: User Defined Exception in Java

## Aim
To write a program to create a User Defined Exception in Java.

## Theory
Java allows programmers to create their own exception classes to represent application-specific error conditions. These are called **User Defined Exceptions** (or Custom Exceptions).

**How to create a custom exception:**
- Extend `Exception` → Creates a **checked** exception (caller must handle or declare it).
- Extend `RuntimeException` → Creates an **unchecked** exception (no mandatory handling).

**Syntax:**
```java
class MyException extends Exception {
    MyException(String message) {
        super(message);  // passes message to parent Exception class
    }
}
```

**Throwing a custom exception:**
```java
throw new MyException("Something went wrong");
```

Key points:
- Use checked exceptions for expected, recoverable error conditions.
- Use unchecked exceptions for programming errors or conditions the caller cannot recover from.
- Custom exceptions can have additional fields (e.g., `amount` in `InsufficientFundsException`) for richer error information.

## Algorithm
1. Define `InsufficientFundsException extends Exception` with a `double amount` field and a meaningful message.
2. Define `InvalidAgeException extends RuntimeException` with a message about valid age range.
3. Define `BankAccount` with `deposit()` and `withdraw()` methods. `withdraw()` throws `InsufficientFundsException` when balance is insufficient.
4. Define `VoterRegistration.register()` that throws `InvalidAgeException` if age < 18 or > 150.
5. In `Runner7.main()`:
   - Create a `BankAccount`, deposit, withdraw (valid then invalid amount), catch and print exception details.
   - Register valid voters, then register invalid ages and catch `InvalidAgeException`.

## Code

### practical7.java
```java
// Aim: Write a program to create a User Defined Exception in Java.

// Checked custom exception
class InsufficientFundsException extends Exception {
    private double amount;
    InsufficientFundsException(double amt) { super("Insufficient funds! Short by: Rs. " + amt); this.amount = amt; }
    double getAmount() { return amount; }
}

// Unchecked custom exception
class InvalidAgeException extends RuntimeException {
    InvalidAgeException(int age) { super("Invalid age: " + age + ". Age must be between 0 and 150."); }
}

class BankAccount {
    private String owner; private double balance;
    BankAccount(String o, double b) { owner = o; balance = b; }
    void deposit(double a) { balance += a; System.out.println("Deposited Rs. " + a + " | Balance: Rs. " + balance); }
    void withdraw(double a) throws InsufficientFundsException {
        if (a > balance) throw new InsufficientFundsException(a - balance);
        balance -= a; System.out.println("Withdrawn Rs. " + a + " | Balance: Rs. " + balance);
    }
    double getBalance() { return balance; }
}

class VoterRegistration {
    static void register(String name, int age) {
        if (age < 18 || age > 150) throw new InvalidAgeException(age);
        System.out.println(name + " (age " + age + ") registered as voter successfully.");
    }
}

class Runner7 {
    public static void main(String[] args) {
        System.out.println("===== Checked Custom Exception =====");
        BankAccount acc = new BankAccount("Pranay", 5000.0);
        acc.deposit(2000.0);
        try { acc.withdraw(3000.0); acc.withdraw(10000.0); }
        catch (InsufficientFundsException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Short by: Rs. " + e.getAmount());
        }
        System.out.println("Final Balance: Rs. " + acc.getBalance());

        System.out.println("\n===== Unchecked Custom Exception =====");
        VoterRegistration.register("Pranay", 20);
        VoterRegistration.register("Sita", 35);
        try { VoterRegistration.register("Raju", 15); }
        catch (InvalidAgeException e) { System.out.println("Exception: " + e.getMessage()); }
        try { VoterRegistration.register("Ghost", 200); }
        catch (InvalidAgeException e) { System.out.println("Exception: " + e.getMessage()); }
    }
}
```

## Output
```
===== Checked Custom Exception =====
Deposited Rs. 2000.0 | Balance: Rs. 7000.0
Withdrawn Rs. 3000.0 | Balance: Rs. 4000.0
Exception: Insufficient funds! Short by: Rs. 6000.0
Short by: Rs. 6000.0
Final Balance: Rs. 4000.0

===== Unchecked Custom Exception =====
Pranay (age 20) registered as voter successfully.
Sita (age 35) registered as voter successfully.
Exception: Invalid age: 15. Age must be between 0 and 150.
Exception: Invalid age: 200. Age must be between 0 and 150.
```

## Result
The program successfully demonstrates the creation and use of user defined exceptions in Java. `InsufficientFundsException` (checked) ensures that insufficient withdrawal is handled at compile time. `InvalidAgeException` (unchecked) validates age at runtime. Both exceptions carry meaningful error messages, showing how custom exceptions make error reporting more specific and informative than generic Java exceptions.

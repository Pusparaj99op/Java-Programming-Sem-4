// Aim: Write a program to create a User Defined Exception in Java.

// A user-defined (custom) exception is created by extending:
//   - Exception          → for a checked exception
//   - RuntimeException   → for an unchecked exception

// =================================================================================================
// 1. Checked custom exception — extends Exception

class InsufficientFundsException extends Exception {
    private double amount;  // how much was short

    InsufficientFundsException(double amount) {
        super("Insufficient funds! Short by: Rs. " + amount);
        this.amount = amount;
    }

    double getAmount() { return amount; }
}

// =================================================================================================
// 2. Unchecked custom exception — extends RuntimeException

class InvalidAgeException extends RuntimeException {
    InvalidAgeException(int age) {
        super("Invalid age: " + age + ". Age must be between 0 and 150.");
    }
}

// =================================================================================================
// BankAccount uses the checked exception

class BankAccount {
    private String owner;
    private double balance;

    BankAccount(String owner, double initialBalance) {
        this.owner   = owner;
        this.balance = initialBalance;
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited Rs. " + amount + " | Balance: Rs. " + balance);
    }

    void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount - balance);
        }
        balance -= amount;
        System.out.println("Withdrawn Rs. " + amount + " | Balance: Rs. " + balance);
    }

    double getBalance() { return balance; }
}

// =================================================================================================
// VoterRegistration uses the unchecked exception

class VoterRegistration {
    static void register(String name, int age) {
        if (age < 18 || age > 150) {
            throw new InvalidAgeException(age);
        }
        System.out.println(name + " (age " + age + ") registered as voter successfully.");
    }
}

// =================================================================================================
// Runner class — entry point

class Runner7 {
    public static void main(String[] args) {

        // ---- Checked custom exception ----
        System.out.println("===== Checked Custom Exception — BankAccount =====");
        BankAccount account = new BankAccount("Pranay", 5000.0);
        account.deposit(2000.0);

        try {
            account.withdraw(3000.0);   // should succeed
            account.withdraw(10000.0);  // should throw InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("Short amount   : Rs. " + e.getAmount());
        }

        System.out.println("Final Balance  : Rs. " + account.getBalance());

        // ---- Unchecked custom exception ----
        System.out.println("\n===== Unchecked Custom Exception — Voter Registration =====");

        // Valid registrations
        VoterRegistration.register("Pranay", 20);
        VoterRegistration.register("Sita", 35);

        // Invalid — too young
        try {
            VoterRegistration.register("Raju", 15);
        } catch (InvalidAgeException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        // Invalid — unrealistic age
        try {
            VoterRegistration.register("Ghost", 200);
        } catch (InvalidAgeException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}

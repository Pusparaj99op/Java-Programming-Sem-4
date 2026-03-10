// Assignment 2 - Q3: Write a program to demonstrate thread synchronization.

class BankAccount {
    private double balance;
    BankAccount(double initial) { this.balance = initial; }

    synchronized void deposit(double amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposited " + amount + " | Balance: " + balance);
    }

    synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew " + amount + " | Balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " - Insufficient funds for " + amount);
        }
    }
}

class Q3_Synchronization {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(1000.0);

        Thread depositor = new Thread(() -> {
            for (int i = 0; i < 3; i++) account.deposit(200.0);
        }, "Depositor");

        Thread withdrawer = new Thread(() -> {
            for (int i = 0; i < 3; i++) account.withdraw(300.0);
        }, "Withdrawer");

        depositor.start();
        withdrawer.start();
        depositor.join();
        withdrawer.join();
        System.out.println("Transactions complete.");
    }
}

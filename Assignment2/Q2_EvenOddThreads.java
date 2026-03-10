// Assignment 2 – Q2
// Aim: Create two threads:
//      Thread 1 – prints all even numbers from 1 to 100.
//      Thread 2 – prints all odd  numbers from 100 to 1.

// =================================================================================================
// Thread 1: Print even numbers from 1 to 100

class EvenThread extends Thread {

    EvenThread() {
        super("Even-Thread");
    }

    @Override
    public void run() {
        System.out.println("[" + getName() + "] Starting even numbers (1 to 100):");
        for (int i = 2; i <= 100; i += 2) {
            System.out.println("[" + getName() + "] " + i);
            try {
                Thread.sleep(10);   // small delay to interleave output with Thread 2
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
            }
        }
        System.out.println("[" + getName() + "] Done.");
    }
}

// =================================================================================================
// Thread 2: Print odd numbers from 100 to 1

class OddThread extends Thread {

    OddThread() {
        super("Odd-Thread");
    }

    @Override
    public void run() {
        System.out.println("[" + getName() + "] Starting odd numbers (100 to 1):");
        for (int i = 99; i >= 1; i -= 2) {
            System.out.println("[" + getName() + "] " + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
            }
        }
        System.out.println("[" + getName() + "] Done.");
    }
}

// =================================================================================================
// Runner class – entry point

class Q2_EvenOddThreads {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("===== Two Threads: Even (1→100) & Odd (100→1) =====\n");

        EvenThread evenThread = new EvenThread();
        OddThread  oddThread  = new OddThread();

        // Start both threads
        evenThread.start();
        oddThread.start();

        // Wait for both to complete
        evenThread.join();
        oddThread.join();

        System.out.println("\nBoth threads have completed.");
    }
}

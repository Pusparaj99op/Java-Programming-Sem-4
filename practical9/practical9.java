// Aim: Write a program on Multi-Threading and use various methods of Thread class in Java.

// Multi-Threading concepts covered:
// 1. Creating a thread by extending Thread class
// 2. Creating a thread by implementing Runnable interface
// 3. Thread lifecycle methods: sleep(), join(), getName(), setName(), getPriority(), setPriority()
// 4. Synchronization to avoid race conditions

// =================================================================================================
// 1. Thread by extending Thread class

class MyThread extends Thread {
    private String taskName;

    MyThread(String name, String taskName) {
        super(name);         // sets the thread name
        this.taskName = taskName;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(getName() + " | " + taskName + " | Step " + i);
            try {
                Thread.sleep(300);  // sleep for 300 ms
            } catch (InterruptedException e) {
                System.out.println(getName() + " was interrupted.");
            }
        }
        System.out.println(getName() + " finished.");
    }
}

// =================================================================================================
// 2. Thread by implementing Runnable interface

class MyRunnable implements Runnable {
    private String name;

    MyRunnable(String name) { this.name = name; }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Runnable-" + name + " | Step " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Runnable-" + name + " interrupted.");
            }
        }
        System.out.println("Runnable-" + name + " finished.");
    }
}

// =================================================================================================
// 3. Synchronization example — shared counter

class SharedCounter {
    private int count = 0;

    // synchronized method: only one thread at a time
    synchronized void increment() {
        count++;
    }

    int getCount() { return count; }
}

// =================================================================================================
// Runner class — entry point

class Runner9 {
    public static void main(String[] args) throws InterruptedException {

        // ---- 1. Thread by extending Thread class ----
        System.out.println("===== Extending Thread Class =====");
        MyThread t1 = new MyThread("Thread-1", "Download");
        MyThread t2 = new MyThread("Thread-2", "Upload");

        System.out.println("Thread-1 Priority: " + t1.getPriority());
        t1.setPriority(Thread.MAX_PRIORITY);   // priority 10
        t2.setPriority(Thread.MIN_PRIORITY);   // priority 1
        System.out.println("Thread-1 Priority after set: " + t1.getPriority());
        System.out.println("Thread-2 Priority after set: " + t2.getPriority());

        t1.start();
        t2.start();
        t1.join();  // wait for t1 to finish
        t2.join();  // wait for t2 to finish

        // ---- 2. Thread via Runnable interface ----
        System.out.println("\n===== Implementing Runnable Interface =====");
        Thread r1 = new Thread(new MyRunnable("Alpha"));
        Thread r2 = new Thread(new MyRunnable("Beta"));
        r1.setName("Runnable-Thread-A");
        r2.setName("Runnable-Thread-B");
        r1.start();
        r2.start();
        r1.join();
        r2.join();

        // ---- 3. Synchronization ----
        System.out.println("\n===== Synchronization =====");
        SharedCounter counter = new SharedCounter();

        Thread incThread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        }, "IncremenThread-1");

        Thread incThread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        }, "IncremenThread-2");

        incThread1.start();
        incThread2.start();
        incThread1.join();
        incThread2.join();

        System.out.println("Final counter value (expected 2000): " + counter.getCount());

        // ---- 4. Thread info methods ----
        System.out.println("\n===== Thread Info =====");
        Thread current = Thread.currentThread();
        System.out.println("Current thread name   : " + current.getName());
        System.out.println("Current thread priority: " + current.getPriority());
        System.out.println("Is daemon thread?      : " + current.isDaemon());
        System.out.println("Thread state           : " + current.getState());
    }
}

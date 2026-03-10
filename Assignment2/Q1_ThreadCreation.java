// Assignment 2 – Q1
// Aim: Explain different ways of creating threads with suitable example.

// There are three main ways to create a thread in Java:
// 1. By extending the Thread class
// 2. By implementing the Runnable interface
// 3. Using an anonymous class / lambda expression (Runnable)

// =================================================================================================
// Way 1: Extending the Thread class

class ThreadByExtension extends Thread {
    private String taskName;

    ThreadByExtension(String threadName, String taskName) {
        super(threadName);          // sets the thread name via Thread constructor
        this.taskName = taskName;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("[Way 1 – Extend] " + getName() + " doing '" + taskName + "' step " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("[Way 1 – Extend] " + getName() + " finished.");
    }
}

// =================================================================================================
// Way 2: Implementing the Runnable interface

class ThreadByRunnable implements Runnable {
    private String taskName;

    ThreadByRunnable(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        String tName = Thread.currentThread().getName();
        for (int i = 1; i <= 3; i++) {
            System.out.println("[Way 2 – Runnable] " + tName + " doing '" + taskName + "' step " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("[Way 2 – Runnable] " + tName + " finished.");
    }
}

// =================================================================================================
// Runner class – entry point

class Q1_ThreadCreation {
    public static void main(String[] args) throws InterruptedException {

        // ---- Way 1: Extending Thread ----
        System.out.println("===== Way 1: Extending Thread Class =====");
        ThreadByExtension t1 = new ThreadByExtension("Ext-Thread-1", "Download");
        ThreadByExtension t2 = new ThreadByExtension("Ext-Thread-2", "Upload");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // ---- Way 2: Implementing Runnable ----
        System.out.println("\n===== Way 2: Implementing Runnable Interface =====");
        Thread r1 = new Thread(new ThreadByRunnable("Print"), "Run-Thread-1");
        Thread r2 = new Thread(new ThreadByRunnable("Scan"),  "Run-Thread-2");
        r1.start();
        r2.start();
        r1.join();
        r2.join();

        // ---- Way 3: Anonymous class / Lambda ----
        System.out.println("\n===== Way 3: Anonymous Class (Runnable) =====");
        Thread a1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 3; i++) {
                    System.out.println("[Way 3 – Anonymous] Anon-Thread step " + i);
                    try { Thread.sleep(200); } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.println("[Way 3 – Anonymous] Anon-Thread finished.");
            }
        }, "Anon-Thread");

        System.out.println("\n===== Way 3 (continued): Lambda Expression =====");
        Thread l1 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("[Way 3 – Lambda] Lambda-Thread step " + i);
                try { Thread.sleep(200); } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("[Way 3 – Lambda] Lambda-Thread finished.");
        }, "Lambda-Thread");

        a1.start();
        l1.start();
        a1.join();
        l1.join();

        System.out.println("\nAll threads have finished execution.");
    }
}

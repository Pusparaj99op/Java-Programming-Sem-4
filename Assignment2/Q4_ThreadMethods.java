// Assignment 2 - Q4: Write a program to demonstrate thread methods: sleep(), join(), setPriority(), getName().

class WorkerThread extends Thread {
    WorkerThread(String name, int priority) {
        super(name);
        setPriority(priority);
    }
    public void run() {
        System.out.println(getName() + " started | Priority: " + getPriority() + " | State: " + getState());
        for (int i = 1; i <= 3; i++) {
            System.out.println(getName() + " working step " + i);
            try { Thread.sleep(100); } catch (InterruptedException e) { }
        }
        System.out.println(getName() + " finished.");
    }
}

class Q4_ThreadMethods {
    public static void main(String[] args) throws InterruptedException {
        WorkerThread high = new WorkerThread("HighPriority", Thread.MAX_PRIORITY);
        WorkerThread low  = new WorkerThread("LowPriority",  Thread.MIN_PRIORITY);

        System.out.println("Starting threads...");
        high.start();
        low.start();

        high.join();   // wait for high priority thread to complete
        low.join();    // wait for low priority thread to complete

        Thread current = Thread.currentThread();
        System.out.println("\nMain thread: " + current.getName()
                + " | Priority: " + current.getPriority()
                + " | State: " + current.getState());
    }
}

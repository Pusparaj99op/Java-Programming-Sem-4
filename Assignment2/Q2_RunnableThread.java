// Assignment 2 - Q2: Write a program to create a thread by implementing the Runnable interface.

class PrintTask implements Runnable {
    private String label;
    PrintTask(String label) { this.label = label; }
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(label + " - step " + i);
            try { Thread.sleep(150); } catch (InterruptedException e) { }
        }
    }
}

class Q2_RunnableThread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Creating threads by implementing Runnable:");
        Thread t1 = new Thread(new PrintTask("Task-X"));
        Thread t2 = new Thread(new PrintTask("Task-Y"));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Both tasks finished.");
    }
}

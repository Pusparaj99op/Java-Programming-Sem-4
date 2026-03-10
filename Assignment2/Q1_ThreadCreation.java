// Assignment 2 - Q1: Write a program to create a thread by extending the Thread class.

class DownloadThread extends Thread {
    DownloadThread(String name) { super(name); }
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " - step " + i);
            try { Thread.sleep(200); } catch (InterruptedException e) { }
        }
    }
}

class Q1_ThreadCreation {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Creating threads by extending Thread class:");
        DownloadThread t1 = new DownloadThread("Thread-A");
        DownloadThread t2 = new DownloadThread("Thread-B");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Both threads finished.");
    }
}

// Assignment 2 - Q5: Write a program to demonstrate inter-thread communication using wait() and notify().

class MessageBox {
    private String message;
    private boolean hasMessage = false;

    synchronized void put(String msg) throws InterruptedException {
        while (hasMessage) wait();          // wait until consumer reads the previous message
        message = msg;
        hasMessage = true;
        System.out.println("Producer put: " + message);
        notify();                           // notify consumer
    }

    synchronized String get() throws InterruptedException {
        while (!hasMessage) wait();         // wait until producer puts a message
        hasMessage = false;
        notify();                           // notify producer
        return message;
    }
}

class Producer implements Runnable {
    private MessageBox box;
    Producer(MessageBox box) { this.box = box; }
    public void run() {
        String[] messages = {"Hello", "World", "Java", "Threads", "Done"};
        for (String msg : messages) {
            try { box.put(msg); Thread.sleep(100); } catch (InterruptedException e) { }
        }
    }
}

class Consumer implements Runnable {
    private MessageBox box;
    Consumer(MessageBox box) { this.box = box; }
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                String msg = box.get();
                System.out.println("Consumer got: " + msg);
            } catch (InterruptedException e) { }
        }
    }
}

class Q5_InterThreadCommunication {
    public static void main(String[] args) throws InterruptedException {
        MessageBox box = new MessageBox();
        Thread producer = new Thread(new Producer(box), "Producer");
        Thread consumer = new Thread(new Consumer(box), "Consumer");
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("Inter-thread communication complete.");
    }
}

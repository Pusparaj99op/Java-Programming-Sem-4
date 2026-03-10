// Assignment 2 – Q3
// Aim: Explain the following thread methods with examples:
//      getName(), setName(), currentThread(), isAlive(),
//      sleep(), wait(), notify(), notifyAll()

// =================================================================================================
// Shared resource used to demonstrate wait / notify / notifyAll

class SharedResource {
    private boolean dataReady = false;

    // Producer: produces data and notifies waiting consumer(s)
    synchronized void produce(String producerName) {
        System.out.println("[" + producerName + "] Producing data...");
        try {
            Thread.sleep(500);          // simulate work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        dataReady = true;
        System.out.println("[" + producerName + "] Data ready. Calling notifyAll().");
        notifyAll();                    // wake up all waiting threads
    }

    // Consumer: waits until data is ready, then consumes it
    synchronized void consume(String consumerName) {
        while (!dataReady) {
            System.out.println("[" + consumerName + "] Data not ready yet. Calling wait().");
            try {
                wait();                 // releases lock and waits for notify/notifyAll
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;                 // exit to avoid re-entering wait() with interrupt set
            }
        }
        System.out.println("[" + consumerName + "] Data consumed.");
    }
}

// =================================================================================================
// Runner class – entry point

class Q3_ThreadMethods {
    public static void main(String[] args) throws InterruptedException {

        // ------------------------------------------------------------------
        // 1. getName() and setName()
        // ------------------------------------------------------------------
        System.out.println("===== 1. getName() and setName() =====");

        Thread t1 = new Thread(() -> {
            System.out.println("  Inside thread – getName(): " + Thread.currentThread().getName());
        });

        // getName() before setting a custom name
        System.out.println("Default name : " + t1.getName());

        // setName() – assign a meaningful name
        t1.setName("Worker-Thread-1");
        System.out.println("After setName: " + t1.getName());

        t1.start();
        t1.join();

        // ------------------------------------------------------------------
        // 2. currentThread()
        // ------------------------------------------------------------------
        System.out.println("\n===== 2. currentThread() =====");

        Thread main = Thread.currentThread();
        System.out.println("Current thread name    : " + main.getName());
        System.out.println("Current thread priority: " + main.getPriority());
        System.out.println("Current thread state   : " + main.getState());

        Thread t2 = new Thread(() -> {
            Thread ct = Thread.currentThread();
            System.out.println("  Inside t2 – currentThread().getName(): " + ct.getName());
        }, "Demo-Thread-2");
        t2.start();
        t2.join();

        // ------------------------------------------------------------------
        // 3. isAlive()
        // ------------------------------------------------------------------
        System.out.println("\n===== 3. isAlive() =====");

        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }, "Alive-Demo-Thread");

        System.out.println("isAlive() before start : " + t3.isAlive());   // false
        t3.start();
        System.out.println("isAlive() after start  : " + t3.isAlive());   // true (most likely)
        t3.join();
        System.out.println("isAlive() after join   : " + t3.isAlive());   // false

        // ------------------------------------------------------------------
        // 4. sleep()
        // ------------------------------------------------------------------
        System.out.println("\n===== 4. sleep() =====");

        System.out.println("Sleeping for 600 ms...");
        long before = System.currentTimeMillis();
        Thread.sleep(600);                            // pauses current thread for 600 ms
        long after  = System.currentTimeMillis();
        System.out.println("Woke up after ~" + (after - before) + " ms.");

        // ------------------------------------------------------------------
        // 5. wait(), notify(), notifyAll()
        // ------------------------------------------------------------------
        System.out.println("\n===== 5. wait() / notify() / notifyAll() =====");

        SharedResource resource = new SharedResource();

        // Two consumer threads waiting for the producer
        Thread consumer1 = new Thread(() -> resource.consume("Consumer-1"), "Consumer-1");
        Thread consumer2 = new Thread(() -> resource.consume("Consumer-2"), "Consumer-2");

        // Producer thread
        Thread producer = new Thread(() -> resource.produce("Producer"), "Producer");

        consumer1.start();
        consumer2.start();
        Thread.sleep(100);    // ensure consumers are waiting before producer runs
        producer.start();

        consumer1.join();
        consumer2.join();
        producer.join();

        // Brief explanation note
        System.out.println("\n--- Summary ---");
        System.out.println("wait()     : releases the lock and suspends the current thread until notified.");
        System.out.println("notify()   : wakes up ONE thread waiting on the same object's monitor.");
        System.out.println("notifyAll(): wakes up ALL threads waiting on the same object's monitor.");

        System.out.println("\nAll demonstrations complete.");
    }
}

# Assignment 2: Multi-Threading in Java

## Overview
This assignment covers the fundamental concepts of Multi-Threading in Java through five questions. Each question demonstrates a different aspect of creating and managing threads.

---

## Q1: Thread Creation using Thread Class

### Aim
To write a Java program to create a thread by extending the `Thread` class.

### Theory
Java provides two ways to create a thread. The first is by **extending the `Thread` class** and overriding its `run()` method. The thread is started by calling `start()`, which internally calls `run()` in a new thread of execution.

Key points:
- A class extending `Thread` must override `run()` to define the thread's task.
- `start()` creates a new thread; calling `run()` directly does NOT create a new thread.
- `join()` makes the calling thread wait until the target thread finishes.

### Algorithm
1. Define `DownloadThread` extending `Thread`. Accept a name via the super constructor.
2. Override `run()` to print 5 steps with a `sleep(200)` pause between steps.
3. In `Q1_ThreadCreation.main()`, create two `DownloadThread` objects (`Thread-A`, `Thread-B`).
4. Call `start()` on both to run them concurrently.
5. Call `join()` on both to wait until they complete before printing the final message.

### Code

```java
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
```

### Output
```
Creating threads by extending Thread class:
Thread-A - step 1
Thread-B - step 1
Thread-A - step 2
Thread-B - step 2
Thread-A - step 3
Thread-B - step 3
Thread-A - step 4
Thread-B - step 4
Thread-A - step 5
Thread-B - step 5
Both threads finished.
```
*(Note: Thread interleaving order may vary between runs)*

### Result
The program successfully demonstrates thread creation by extending the `Thread` class. Both `Thread-A` and `Thread-B` run concurrently, interleaving their output. `join()` ensures the main thread waits for both to complete before printing the final message.

---

## Q2: Thread Creation using Runnable Interface

### Aim
To write a Java program to create a thread by implementing the `Runnable` interface.

### Theory
The second way to create a thread in Java is by **implementing the `Runnable` interface**. The `run()` method is implemented in the class, and a `Thread` object is created by passing the `Runnable` instance to the `Thread` constructor.

Key points:
- Preferred approach when the class already extends another class (Java does not support multiple inheritance).
- Separates the task logic (`Runnable`) from the thread management (`Thread`).
- The same `Runnable` object can be reused with multiple `Thread` objects.

### Algorithm
1. Define `PrintTask` implementing `Runnable`. Store a `label` string.
2. Implement `run()` to print 5 steps with a `sleep(150)` pause.
3. In `Q2_RunnableThread.main()`, create two `Thread` objects wrapping `PrintTask` instances.
4. Call `start()` on both threads.
5. Call `join()` on both and print the completion message.

### Code

```java
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
```

### Output
```
Creating threads by implementing Runnable:
Task-X - step 1
Task-Y - step 1
Task-X - step 2
Task-Y - step 2
Task-X - step 3
Task-Y - step 3
Task-X - step 4
Task-Y - step 4
Task-X - step 5
Task-Y - step 5
Both tasks finished.
```
*(Note: Thread interleaving order may vary between runs)*

### Result
The program successfully demonstrates thread creation using the `Runnable` interface. `Task-X` and `Task-Y` run concurrently as separate threads. This approach is more flexible than extending `Thread` because the task class is free to extend other classes.

---

## Q3: Thread Synchronization

### Aim
To write a Java program to demonstrate thread synchronization using the `synchronized` keyword.

### Theory
When multiple threads access a shared resource simultaneously, it can lead to **race conditions** and inconsistent results. Java's `synchronized` keyword ensures that only one thread can execute a method or block at a time, preventing data corruption.

Key points:
- A `synchronized` method acquires the object's intrinsic lock before executing.
- Other threads trying to call a `synchronized` method on the same object are blocked until the lock is released.
- Synchronization prevents race conditions but may reduce performance if overused.

### Algorithm
1. Define `BankAccount` with a `balance` field.
2. Implement `synchronized deposit(double)` that adds the amount and prints the new balance.
3. Implement `synchronized withdraw(double)` that subtracts if funds are sufficient.
4. In `Q3_Synchronization.main()`, create a `BankAccount` with balance 1000.
5. Create a `Depositor` thread that deposits 200.0 three times.
6. Create a `Withdrawer` thread that withdraws 300.0 three times.
7. Start both threads, join them, and print completion message.

### Code

```java
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
```

### Output
```
Depositor deposited 200.0 | Balance: 1200.0
Depositor deposited 200.0 | Balance: 1400.0
Depositor deposited 200.0 | Balance: 1600.0
Withdrawer withdrew 300.0 | Balance: 1300.0
Withdrawer withdrew 300.0 | Balance: 1000.0
Withdrawer withdrew 300.0 | Balance: 700.0
Transactions complete.
```
*(Note: Thread interleaving order may vary between runs)*

### Result
The program successfully demonstrates thread synchronization. The `synchronized` methods on `BankAccount` ensure that deposit and withdrawal operations are atomic — no two threads modify the balance at the same time. This prevents race conditions and ensures data consistency.

---

## Q4: Thread Methods

### Aim
To write a Java program to demonstrate important thread methods: `sleep()`, `join()`, `setPriority()`, `getPriority()`, `getName()`, and `getState()`.

### Theory
The `Thread` class provides several methods to manage and inspect threads:

| Method | Description |
|--------|-------------|
| `sleep(ms)` | Pauses the current thread for the given milliseconds |
| `join()` | Makes the calling thread wait until this thread finishes |
| `setPriority(n)` | Sets the thread priority (1 = MIN, 5 = NORM, 10 = MAX) |
| `getPriority()` | Returns the thread's current priority |
| `getName()` / `setName()` | Gets or sets the thread's name |
| `getState()` | Returns the thread's state (NEW, RUNNABLE, WAITING, etc.) |
| `currentThread()` | Returns a reference to the currently executing thread |

### Algorithm
1. Define `WorkerThread` extending `Thread`. Accept a name and priority in the constructor.
2. Call `setPriority()` in the constructor to assign the given priority.
3. Override `run()` to print the thread name, priority, and state, then work 3 steps with `sleep(100)`.
4. In `Q4_ThreadMethods.main()`, create a high-priority thread (`MAX_PRIORITY = 10`) and a low-priority thread (`MIN_PRIORITY = 1`).
5. Start both threads, join both, and print the main thread's info using `Thread.currentThread()`.

### Code

```java
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

        high.join();
        low.join();

        Thread current = Thread.currentThread();
        System.out.println("\nMain thread: " + current.getName()
                + " | Priority: " + current.getPriority()
                + " | State: " + current.getState());
    }
}
```

### Output
```
Starting threads...
HighPriority started | Priority: 10 | State: RUNNABLE
LowPriority started | Priority: 1 | State: RUNNABLE
HighPriority working step 1
LowPriority working step 1
HighPriority working step 2
LowPriority working step 2
HighPriority working step 3
LowPriority working step 3
HighPriority finished.
LowPriority finished.

Main thread: main | Priority: 5 | State: RUNNABLE
```
*(Note: Thread interleaving order may vary; priority is a hint to the JVM scheduler, not a strict guarantee)*

### Result
The program successfully demonstrates key thread methods. `sleep()` pauses each thread between steps, `join()` makes the main thread wait, `setPriority()` assigns thread priorities, and `getName()`/`getPriority()`/`getState()` retrieve thread information. The main thread shows its default priority of 5.

---

## Q5: Inter-Thread Communication

### Aim
To write a Java program to demonstrate inter-thread communication using `wait()` and `notify()`.

### Theory
**Inter-thread communication** allows threads to coordinate with each other. Java provides three methods in `Object` for this purpose:

| Method | Description |
|--------|-------------|
| `wait()` | Releases the lock and puts the thread into a waiting state |
| `notify()` | Wakes up one thread that is waiting on the object's lock |
| `notifyAll()` | Wakes up all threads waiting on the object's lock |

These methods must be called from within a `synchronized` context (method or block). The classic use case is the **Producer-Consumer** pattern, where a producer thread creates data and a consumer thread processes it.

### Algorithm
1. Define `MessageBox` with a `message` string and a `hasMessage` boolean flag.
2. Implement `synchronized put(String)`: if `hasMessage` is true, call `wait()`. Otherwise, store the message, set `hasMessage = true`, print it, and call `notify()`.
3. Implement `synchronized get()`: if `hasMessage` is false, call `wait()`. Otherwise, set `hasMessage = false`, call `notify()`, and return the message.
4. Define `Producer` (implements `Runnable`): calls `put()` for 5 messages.
5. Define `Consumer` (implements `Runnable`): calls `get()` 5 times and prints each message.
6. In `Q5_InterThreadCommunication.main()`, create a shared `MessageBox`, start Producer and Consumer threads, and join both.

### Code

```java
// Assignment 2 - Q5: Write a program to demonstrate inter-thread communication using wait() and notify().

class MessageBox {
    private String message;
    private boolean hasMessage = false;

    synchronized void put(String msg) throws InterruptedException {
        while (hasMessage) wait();
        message = msg;
        hasMessage = true;
        System.out.println("Producer put: " + message);
        notify();
    }

    synchronized String get() throws InterruptedException {
        while (!hasMessage) wait();
        hasMessage = false;
        notify();
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
```

### Output
```
Producer put: Hello
Consumer got: Hello
Producer put: World
Consumer got: World
Producer put: Java
Consumer got: Java
Producer put: Threads
Consumer got: Threads
Producer put: Done
Consumer got: Done
Inter-thread communication complete.
```

### Result
The program successfully demonstrates inter-thread communication in Java. The `Producer` and `Consumer` threads communicate through a shared `MessageBox`. `wait()` suspends a thread when it cannot proceed, and `notify()` wakes up the waiting thread when the condition changes. This pattern avoids busy-waiting and ensures correct sequencing of messages.

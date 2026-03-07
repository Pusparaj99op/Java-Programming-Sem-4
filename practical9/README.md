# Practical 9: Multi-Threading in Java

## Aim
To write a program on Multi-Threading and use various methods of the Thread class in Java.

## Theory
**Multi-Threading** is the concurrent execution of two or more threads. A **thread** is the smallest unit of a process. Java supports built-in multi-threading through the `java.lang.Thread` class and `java.lang.Runnable` interface.

**Ways to create a thread:**
1. **Extending `Thread` class**: Override the `run()` method and call `start()`.
2. **Implementing `Runnable` interface**: Implement `run()`, pass object to `new Thread()`, and call `start()`.

**Important Thread methods:**

| Method | Description |
|--------|-------------|
| `start()` | Starts the thread (calls `run()` internally) |
| `run()` | Defines the task executed by the thread |
| `sleep(ms)` | Pauses the current thread for given milliseconds |
| `join()` | Waits for thread to finish before continuing |
| `getName()` / `setName()` | Gets/sets the thread name |
| `getPriority()` / `setPriority()` | Gets/sets priority (1–10; default 5) |
| `currentThread()` | Returns reference to the currently executing thread |
| `getState()` | Returns thread state (NEW, RUNNABLE, WAITING, etc.) |

**Synchronization**: When multiple threads share a resource, a `synchronized` method/block ensures only one thread accesses it at a time, preventing race conditions.

## Algorithm
1. Define `MyThread extends Thread`. Override `run()` to print step messages with `sleep(300)`.
2. Define `MyRunnable implements Runnable`. Implement `run()` similarly.
3. Define `SharedCounter` with a `synchronized increment()` method.
4. In `Runner9.main()`:
   - Create two `MyThread` objects, set priorities, call `start()` and `join()`.
   - Create two `Thread` objects with `MyRunnable`, set names, start and join.
   - Create two threads both incrementing `SharedCounter` 1000 times each; verify final count = 2000.
   - Print current thread info using `Thread.currentThread()`.

## Code

### practical9.java
```java
// Aim: Write a program on Multi-Threading and use various methods of Thread class in Java.

class MyThread extends Thread {
    private String taskName;
    MyThread(String name, String task) { super(name); this.taskName = task; }
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(getName() + " | " + taskName + " | Step " + i);
            try { Thread.sleep(300); } catch (InterruptedException e) { }
        }
    }
}

class MyRunnable implements Runnable {
    private String name;
    MyRunnable(String n) { name = n; }
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Runnable-" + name + " | Step " + i);
            try { Thread.sleep(200); } catch (InterruptedException e) { }
        }
    }
}

class SharedCounter {
    private int count = 0;
    synchronized void increment() { count++; }
    int getCount() { return count; }
}

class Runner9 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("===== Extending Thread =====");
        MyThread t1 = new MyThread("T1","Download"), t2 = new MyThread("T2","Upload");
        t1.setPriority(Thread.MAX_PRIORITY); t2.setPriority(Thread.MIN_PRIORITY);
        t1.start(); t2.start(); t1.join(); t2.join();

        System.out.println("\n===== Runnable Interface =====");
        Thread r1 = new Thread(new MyRunnable("Alpha")), r2 = new Thread(new MyRunnable("Beta"));
        r1.start(); r2.start(); r1.join(); r2.join();

        System.out.println("\n===== Synchronization =====");
        SharedCounter c = new SharedCounter();
        Thread i1 = new Thread(() -> { for(int i=0;i<1000;i++) c.increment(); });
        Thread i2 = new Thread(() -> { for(int i=0;i<1000;i++) c.increment(); });
        i1.start(); i2.start(); i1.join(); i2.join();
        System.out.println("Final count (expected 2000): " + c.getCount());

        Thread cur = Thread.currentThread();
        System.out.println("\nCurrent thread: " + cur.getName() + " | Priority: " + cur.getPriority() + " | State: " + cur.getState());
    }
}
```

## Output
```
===== Extending Thread =====
T1 | Download | Step 1
T2 | Upload | Step 1
T1 | Download | Step 2
T2 | Upload | Step 2
T1 | Download | Step 3
T2 | Upload | Step 3

===== Runnable Interface =====
Runnable-Alpha | Step 1
Runnable-Beta | Step 1
Runnable-Alpha | Step 2
Runnable-Beta | Step 2
Runnable-Alpha | Step 3
Runnable-Beta | Step 3

===== Synchronization =====
Final count (expected 2000): 2000

Current thread: main | Priority: 5 | State: RUNNABLE
```
*(Note: Thread interleaving order may vary between runs)*

## Result
The program successfully demonstrates multi-threading in Java. Threads created via extending `Thread` and implementing `Runnable` run concurrently. Thread lifecycle methods (`sleep`, `join`, `setPriority`, `getName`) are demonstrated. The synchronized `SharedCounter` correctly reaches 2000 despite concurrent increments, proving that synchronization prevents race conditions.

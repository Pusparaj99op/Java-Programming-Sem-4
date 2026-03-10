# Assignment 2 – Threads, Date & Time

**Page No.:** &nbsp;  
**Date:** 10/03/2026

---

## Q1 – Different Ways of Creating Threads

**Aim:** Explain different ways of creating threads with a suitable example (Code).

Three ways demonstrated:
1. By **extending** the `Thread` class
2. By **implementing** the `Runnable` interface
3. Using an **anonymous class** / **lambda expression** (Runnable)

File: `Q1_ThreadCreation.java`

- [x] Program Done
- [ ] Written
- [ ] Checked

---

## Q2 – Even & Odd Number Threads

**Aim:** Create two threads —  
- Thread 1: Print all **even** numbers from **1 to 100**  
- Thread 2: Print all **odd** numbers from **100 to 1**

File: `Q2_EvenOddThreads.java`

- [x] Program Done
- [ ] Written
- [ ] Checked

---

## Q3 – Thread Methods

**Aim:** Explain and demonstrate the following thread methods:
`getName()`, `setName()`, `currentThread()`, `isAlive()`, `sleep()`, `wait()`, `notify()`, `notifyAll()`

File: `Q3_ThreadMethods.java`

| Method | Description |
|---|---|
| `getName()` | Returns the name of the thread. |
| `setName(String)` | Sets a custom name for the thread. |
| `currentThread()` | Returns a reference to the currently executing thread. |
| `isAlive()` | Returns `true` if the thread has been started and has not yet terminated. |
| `sleep(ms)` | Causes the current thread to sleep for the specified number of milliseconds. |
| `wait()` | Makes the current thread wait until another thread calls `notify()` or `notifyAll()` on the same object. Must be called inside a `synchronized` block. |
| `notify()` | Wakes up **one** thread waiting on the object's monitor. |
| `notifyAll()` | Wakes up **all** threads waiting on the object's monitor. |

- [x] Program Done
- [ ] Written
- [ ] Checked

---

## Q4 – Calendar Class – Retrieve Date & Time Fields

**Aim:** Write a program to retrieve specific date & time fields from the current calendar using the `Calendar` class.

File: `Q4_CalendarDemo.java`

- [x] Program Done
- [ ] Written
- [ ] Checked

---

## Q5 – String to Date using SimpleDateFormat

**Aim:** Write a program to convert a String to a Date using the `SimpleDateFormat` class.

File: `Q5_StringToDate.java`

- [x] Program Done
- [ ] Written
- [ ] Checked

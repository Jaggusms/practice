# Resolving Race Conditions in Java

## 1. What is a race condition?

A **race condition** occurs when multiple threads access shared mutable
data at the same time and at least one thread modifies it. The result
can depend on the order in which the threads execute.

### Example: Race condition without synchronization

``` java
class Counter {
    int count = 0;

    public void increment() {
        count++;
    }
}
```

`count++` is not one indivisible operation. It involves:

1.  Read the current value.
2.  Add one.
3.  Write the new value.

If two threads both read `0` before either writes, both can write `1`.

-   Expected result after two increments: `2`
-   Possible actual result: `1`

This is called a **lost update**.

## 2. Solution 1: `synchronized`

`synchronized` ensures that only one thread at a time can execute a
synchronized section protected by the same monitor lock.

``` java
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
```

### Runnable example

``` java
public class SynchronizedExample {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.getCount()); // 200000
    }
}

class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
```

**Use when:** You need to protect a critical section or several related
operations on shared state.

## 3. Solution 2: `ReentrantLock`

`ReentrantLock` provides explicit lock control. Always release the lock
in a `finally` block so it is released even if an exception occurs.

``` java
import java.util.concurrent.locks.ReentrantLock;

class Counter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}
```

Useful capabilities include:

-   `tryLock()` --- attempt to acquire a lock without waiting
    indefinitely.
-   `lockInterruptibly()` --- allow a thread waiting for the lock to be
    interrupted.
-   Optional fairness policy --- approximate first-come-first-served
    acquisition.

**Use when:** You need lock features or control that `synchronized` does
not provide.

## 4. Solution 3: Atomic classes

For simple thread-safe updates such as incrementing a counter, use
classes from `java.util.concurrent.atomic`.

``` java
import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
```

`AtomicInteger` provides atomic operations. Its common implementation
uses compare-and-set (CAS) techniques.

Other useful atomic classes include:

-   `AtomicLong`
-   `AtomicBoolean`
-   `AtomicReference<T>`

**Use when:** You need atomic updates to a single value, rather than
coordinating a larger group of operations.

## 5. Solution 4: `ConcurrentHashMap`

For concurrent access to a map, prefer `ConcurrentHashMap` over a
regular `HashMap` when multiple threads may update it.

``` java
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

class Example {
    public static void main(String[] args) {
        Map<Integer, String> map = new ConcurrentHashMap<>();

        map.put(1, "Java");
        map.put(2, "Spring");

        System.out.println(map.get(1));
    }
}
```

Individual map operations are thread-safe. For compound logic, use
atomic map methods such as `putIfAbsent`, `compute`, or
`computeIfAbsent` where appropriate.

Example:

``` java
map.compute(1, (key, oldValue) ->
        oldValue == null ? "Java" : oldValue + " Backend");
```

**Use when:** Multiple threads need to read and update a shared map.

## 6. Solution 5: `volatile`

`volatile` guarantees visibility of a variable's latest write to other
threads that read it. It does **not** make compound operations such as
`count++` atomic.

``` java
class Worker {
    private volatile boolean running = true;

    public void stop() {
        running = false;
    }

    public void work() {
        while (running) {
            // Do work
        }
    }
}
```

**Use when:** A shared flag or value is read and written independently,
and visibility is the requirement.

**Do not use it as a replacement for a lock or atomic counter.**

## 7. Solution 6: `ThreadLocal`

`ThreadLocal` gives each thread its own independent copy of a value.
Threads therefore do not update the same variable.

``` java
class ThreadLocalExample {
    private static final ThreadLocal<Integer> count =
            ThreadLocal.withInitial(() -> 0);

    public static void increment() {
        count.set(count.get() + 1);
    }

    public static int getCount() {
        return count.get();
    }
}
```

**Use when:** Data should be isolated per thread rather than shared.

In thread pools, call `remove()` when thread-specific data is no longer
needed, to avoid retaining stale values between tasks.

## 8. Solution 7: Immutable objects

An immutable object cannot be changed after construction. Sharing
immutable state avoids races caused by concurrent modification of that
object's fields.

``` java
public final class Employee {
    private final int id;
    private final String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
```

For immutability, fields should be private and final, there should be no
setters, and mutable values supplied to or returned from the object
should be defensively copied.

**Use when:** Shared data does not need to change after creation.

## 9. Comparison

  -----------------------------------------------------------------------
  Approach                What it provides        Typical use case
  ----------------------- ----------------------- -----------------------
  `synchronized`          Mutual exclusion and    Protecting a critical
                          visibility              section

  `ReentrantLock`         Explicit locking and    Complex locking
                          advanced lock features  requirements

  `AtomicInteger`         Atomic operations on an Shared counter
                          integer                 

  `ConcurrentHashMap`     Thread-safe map         Shared concurrent map
                          operations              

  `volatile`              Visibility and ordering Shared status flag
                          guarantees              

  `ThreadLocal`           Separate value per      Thread-specific context
                          thread                  

  Immutable objects       State cannot be         Shared read-only data
                          modified after          
                          construction            
  -----------------------------------------------------------------------

## 10. Which solution should you choose?

  -----------------------------------------------------------------------
  Scenario                            Recommended approach
  ----------------------------------- -----------------------------------
  Increment a shared counter          `AtomicInteger`

  Protect multiple related operations `synchronized` or `ReentrantLock`

  Need `tryLock()` or interruptible   `ReentrantLock`
  locking                             

  Multiple threads access a shared    `ConcurrentHashMap`
  map                                 

  Share a simple stop/running flag    `volatile`

  Each thread needs independent data  `ThreadLocal`

  Data should never change            Immutable object
  -----------------------------------------------------------------------

## 11. Important interview distinctions

### Race condition

The outcome depends on the timing or interleaving of concurrent
operations on shared state.

### Deadlock

Two or more threads are stuck waiting for locks or resources held by one
another.

### Starvation

A thread repeatedly fails to get the CPU time or resources it needs
because other threads keep getting priority or access.

### Visibility vs atomicity

-   **Visibility:** One thread can see a write made by another thread.
-   **Atomicity:** An operation happens as one indivisible unit.
-   `volatile` helps with visibility; it does not make `count++` atomic.
-   `synchronized` provides mutual exclusion and visibility for code
    protected by the same monitor.
-   Atomic classes provide atomic operations on their values.

## Key takeaway

Choose the smallest tool that correctly protects the shared state:

-   Use `AtomicInteger` for a simple counter.
-   Use `ConcurrentHashMap` for concurrent map operations.
-   Use `synchronized` or `ReentrantLock` when multiple operations must
    be protected together.
-   Use `volatile` for visibility of simple shared state, not compound
    updates.
-   Use immutable objects or `ThreadLocal` when sharing mutable state
    can be avoided.

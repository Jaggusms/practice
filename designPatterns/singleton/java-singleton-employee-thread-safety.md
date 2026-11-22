# Java Singleton Design Pattern --- Employee Examples

## 1. What is Singleton?

The **Singleton Design Pattern** ensures that a class has only one
instance and provides a global access point to that instance.

For example, if `Employee` is a Singleton, every caller receives the
same `Employee` object:

``` java
Employee emp1 = Employee.getInstance();
Employee emp2 = Employee.getInstance();

System.out.println(emp1 == emp2); // true
```

A Singleton implementation normally has:

1.  A `private` constructor, so other classes cannot call
    `new Employee()`.
2.  A `private static` instance, which holds the single object.
3.  A public static access method, such as `getInstance()`.

> Important distinction: **Thread-safe Singleton creation** means
> multiple threads cannot create multiple instances. It does not
> automatically mean that mutable fields inside the single object are
> safe for concurrent access.

------------------------------------------------------------------------

## 2. Normal Singleton --- NOT thread-safe

``` java
public class Employee {

    private static Employee instance;

    private Employee() {
    }

    public static Employee getInstance() {
        if (instance == null) {
            instance = new Employee();
        }

        return instance;
    }
}
```

### Why can it fail?

Two threads can both check `instance == null` before either thread
assigns the new object.

``` text
Thread-1                    Thread-2
   |                           |
checks instance == null    checks instance == null
   |                           |
creates Employee object    creates Employee object
   |                           |
assigns instance           assigns instance
```

Two objects may be created. Therefore, this implementation is **not
thread-safe**.

------------------------------------------------------------------------

## 3. Synchronized method --- thread-safe, lazy

``` java
public class Employee {

    private static Employee instance;

    private Employee() {
    }

    public static synchronized Employee getInstance() {
        if (instance == null) {
            instance = new Employee();
        }

        return instance;
    }
}
```

### How it works

-   `synchronized` on a static method locks the `Employee.class`
    monitor.
-   Only one thread at a time can execute this method.
-   The first thread creates the object.
-   A later thread enters the method, sees that the instance already
    exists, and returns it.

**Advantages** - Simple and thread-safe. - Lazy: the object is created
when `getInstance()` is first called.

**Disadvantage** - Every call acquires the same lock, even after the
object exists.

------------------------------------------------------------------------

## 4. Double-Checked Locking --- thread-safe, lazy

``` java
public class Employee {

    private static volatile Employee instance;

    private Employee() {
    }

    public static Employee getInstance() {

        // First check: avoid locking after the object exists.
        if (instance == null) {

            synchronized (Employee.class) {

                // Second check: another thread may have created it.
                if (instance == null) {
                    instance = new Employee();
                }
            }
        }

        return instance;
    }
}
```

### Why are there two checks?

Imagine Thread-1 and Thread-2 both see `instance == null`.

1.  Thread-1 obtains the class lock and creates the object.
2.  Thread-2 waits for the lock.
3.  Thread-1 releases the lock.
4.  Thread-2 obtains the lock and checks again.
5.  Thread-2 sees the object already exists, so it does not create
    another one.

### Why is `volatile` required?

`volatile` provides visibility between threads and prevents unsafe
reordering of the object-reference publication. It ensures that a thread
that sees the non-null reference also sees the properly constructed
object's state.

**Advantages** - Thread-safe. - Lazy initialization. - Lock is used only
during first initialization; later calls do not enter the synchronized
block.

**Important:** Keep `volatile`. Double-checked locking without
`volatile` is not a correct modern Java implementation.

------------------------------------------------------------------------

## 5. Static inner class / Initialization-on-demand holder --- thread-safe, lazy

``` java
public class Employee {

    private Employee() {
    }

    private static class EmployeeHolder {
        private static final Employee INSTANCE = new Employee();
    }

    public static Employee getInstance() {
        return EmployeeHolder.INSTANCE;
    }
}
```

### How it works

-   Loading `Employee` does not initialize `EmployeeHolder`.
-   The first call to `getInstance()` causes the JVM to initialize
    `EmployeeHolder`.
-   JVM class initialization is synchronized: one thread initializes the
    class, and other threads wait for initialization to finish.
-   The `INSTANCE` is created once and safely published.

**Advantages** - Thread-safe. - Lazy initialization. - No explicit
`synchronized` or `volatile` required. - Clean and efficient.

This is an excellent general-purpose class-based Singleton
implementation.

------------------------------------------------------------------------

## 6. Enum Singleton --- thread-safe

``` java
public enum Employee {

    INSTANCE;

    private int employeeId;
    private String employeeName;

    public void setDetails(int employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public void displayDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
    }
}
```

### Usage

``` java
public class Main {

    public static void main(String[] args) {

        Employee emp1 = Employee.INSTANCE;
        emp1.setDetails(101, "Jagadeesh");

        Employee emp2 = Employee.INSTANCE;
        emp2.displayDetails();

        System.out.println(emp1 == emp2); // true
    }
}
```

### Why is enum Singleton thread-safe?

-   Enum constants are initialized as part of the enum class's
    initialization.
-   JVM class initialization is synchronized.
-   The enum constant is created once per enum class initialization.
-   All threads accessing `Employee.INSTANCE` receive the same constant.

**Enum notes** - The enum constant is initialized when the enum class is
initialized; it is not the lazy-on-first-`INSTANCE`-access approach used
by the holder pattern. - Enum provides strong protection against
ordinary reflection-based construction and serialization-based
duplication. - A mutable enum object's fields are not automatically
thread-safe.

------------------------------------------------------------------------

## 7. Comparison

  --------------------------------------------------------------------------
  Approach                Thread-safe              Lazy?       Lock on every
                            creation?                                access?
  ---------------- ------------------ ------------------ -------------------
  Normal Singleton                 No                Yes                  No

  Synchronized                    Yes                Yes                 Yes
  method                                                 

  Double-checked                  Yes                Yes           No, after
  locking                                                     initialization
  (`volatile`)                                           

  Static inner                    Yes                Yes         No explicit
  class                                                    application-level
                                                                        lock

  Enum Singleton                  Yes    No, initialized                  No
                                         with enum class 
  --------------------------------------------------------------------------

**Recommendation** - Learn all approaches for interviews. - For a
regular class-based Singleton, the static inner class holder is a clean
choice. - Enum is a strong choice when an enum-based Singleton is
suitable. - Use double-checked locking when you specifically need to
understand or implement that pattern.

------------------------------------------------------------------------

## 8. Does thread-safe creation make Employee fields thread-safe?

**No.** Singleton guarantees one object, not safe concurrent updates to
that object's fields.

For example, this enum has one instance, but concurrent writes to its
fields can race:

``` java
public enum Employee {

    INSTANCE;

    private int salary;

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }
}
```

If multiple threads call `setSalary()` and `getSalary()` concurrently,
the application needs a separate thread-safety strategy for the data.

### Option A: Synchronize access to mutable state

``` java
public enum Employee {

    INSTANCE;

    private int salary;

    public synchronized void setSalary(int salary) {
        this.salary = salary;
    }

    public synchronized int getSalary() {
        return salary;
    }
}
```

The same object's monitor is used by both methods. Only one of these
synchronized method executions can hold that monitor at a time.

### Option B: Use volatile for a simple independently read/written value

``` java
public enum Employee {

    INSTANCE;

    private volatile int salary;

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }
}
```

`volatile` makes reads and writes visible across threads. It is suitable
for this simple assignment/read example, but it does **not** make
compound operations such as `salary++` atomic.

For compound updates, use synchronization or an atomic type such as
`AtomicInteger`.

### Option C: Prefer immutable state where possible

``` java
public final class EmployeeDetails {

    private final int employeeId;
    private final String employeeName;

    public EmployeeDetails(int employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }
}
```

Immutable objects are easier to share safely because their state cannot
be changed after construction.

------------------------------------------------------------------------

## 9. Interview-ready summary

**Q: What is Singleton?**

A: Singleton is a creational design pattern that ensures a class has
only one instance and provides a global access point to it.

**Q: Why is a normal Singleton not thread-safe?**

A: Multiple threads can simultaneously observe a null instance and each
create a new object.

**Q: How can you make Singleton thread-safe?**

A: Use a synchronized method, double-checked locking with a volatile
instance, a static inner class holder, or an enum Singleton.

**Q: Why is volatile used in double-checked locking?**

A: It provides visibility and prevents unsafe publication/reordering of
the instance reference.

**Q: Why is the static inner class holder thread-safe?**

A: JVM class initialization is synchronized, so the holder's static
instance is initialized once and safely published.

**Q: Does enum make all its fields thread-safe?**

A: No. Enum guarantees safe creation of the enum constant. Mutable
fields still need synchronization, volatile, atomic types, or another
appropriate concurrency strategy.

**Q: What is the difference between thread-safe creation and thread-safe
state?**

A: - Thread-safe creation: only one Singleton instance is created and
safely published. - Thread-safe state: concurrent access to the object's
mutable fields is coordinated correctly.

------------------------------------------------------------------------

## 10. Quick revision

``` text
Singleton
  |
  +-- Private constructor
  |
  +-- One static instance
  |
  +-- Public access method
        |
        +-- Normal Singleton (not thread-safe)
        +-- Synchronized method
        +-- Double-checked locking (volatile)
        +-- Static inner class holder
        +-- Enum Singleton

Remember:
One instance != automatically thread-safe fields.
```

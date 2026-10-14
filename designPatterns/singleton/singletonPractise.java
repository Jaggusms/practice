package singleton;
/*
JAVA SINGLETON OBJECT — RULES AND WHY WE NEED THEM

1. CLASS
   - Purpose: Defines the Singleton object.
   - Visibility: Can be public or package-private.
   - Why: Controls where the class can be accessed.
   - Required: Yes.

2. CONSTRUCTOR
   - Visibility: private.
   - Why: Prevents other classes from creating objects using new.
   - Required: Yes, for a conventional Singleton implementation.

3. INSTANCE VARIABLE
   - Declaration: private static final Company INSTANCE = new Company();
   - private: Prevents direct external access to the instance field.
   - static: Stores the reference at the class level.
   - final: Prevents reassignment of the reference.
   - Why: Maintains one shared instance reference.
   - Required: Yes, in this eager initialization implementation.

4. INSTANCE ACCESS METHOD
   - Declaration: public static Company getInstance().
   - public: Allows callers to access the instance.
   - static: Allows access without creating another object.
   - Why: Returns the same Singleton instance.
   - Required: Yes, for this implementation.

5. INSTANCE VARIABLES FOR DATA
   - Examples: companyName, location, houseNumber.
   - Visibility: Usually private.
   - Why: Stores the data belonging to the shared Singleton object.
   - Required: Only if the Singleton needs to store data.

6. GETTERS
   - Example: getCompanyName().
   - Visibility: Usually public.
   - Why: Allows callers to read private instance variables.
   - Required: Only if callers need to read those values.

7. SETTERS
   - Example: setCompanyName(String companyName).
   - Visibility: Usually public.
   - Why: Allows callers to modify private instance variables.
   - Required: Only if callers need to change those values.
   - Note: Setters are not mandatory for a Singleton.

8. STATIC DATA VARIABLES
   - Do not make companyName, location, and houseNumber static
     merely because the class is a Singleton.
   - Why: These fields belong to the single object already.
   - Use static fields only when class-level data is specifically needed.

9. OBJECT CREATION
   - Access using: Company.getInstance().
   - Avoid: new Company() outside the class.
   - Why: Ensures callers use the shared instance.

10. THREAD SAFETY
    - Eager initialization is thread-safe under normal Java class initialization.
    - Why: Concurrent callers receive the same initialized instance.
    - Note: Thread-safe initialization does not make mutable fields
      automatically thread-safe.

11. DEPENDENCY INJECTION
    - A manually implemented Singleton does not require dependency injection.
    - Spring can manage singleton-scoped beans automatically.
    - Why: Spring manages object creation and injects the shared bean.
    - Do not combine a private-constructor Singleton with ordinary
      Spring constructor injection without redesigning the class.

12. DATA MODIFICATION
    - A Singleton can have mutable or immutable data.
    - Getters do not break Singleton behavior.
    - Setters do not break Singleton behavior.
    - However, changing a field affects every reference to the same object.
    - Why: All callers share the same instance and its state.
 */
class singletonPractise1 {
    private static final singletonPractise1 instance =
            new singletonPractise1();
    private String companyName ;
    private String location ;
    private Integer housNumber ;

    private  singletonPractise1(){

    }
    public static singletonPractise1 getInstance(){
        return  instance;
    }

    public Integer getHousNumber ( ) {
        return housNumber;
    }

    public String getCompanyName ( ) {
        return companyName;
    }

    public String getLocation ( ) {
        return location;
    }

    public void setCompanyName ( String companyName ) {
        this.companyName = companyName;
    }

    public void setHousNumber ( Integer housNumber ) {
        this.housNumber = housNumber;
    }

    public void setLocation ( String location ) {
        this.location = location;
    }
}

class singletonPractise2 {
    private static final singletonPractise2 instance =
            new singletonPractise2();
    private final String companyName ="google" ;
    private final String location = "hyd" ;
    private final  Integer housNumber = 123 ;

    private  singletonPractise2(){

    }
    public static singletonPractise2 getInstance(){
        return  instance;
    }

    public Integer getHousNumber ( ) {
        return housNumber;
    }

    public String getCompanyName ( ) {
        return companyName;
    }

    public String getLocation ( ) {
        return location;
    }
}


class singletonPractise3 {
    private static final singletonPractise3 instance =
            new singletonPractise3("google","hyd",123);
    private final String companyName  ;
    private final String location  ;
    private final  Integer housNumber;

    private  singletonPractise3(String companyName, String location
            , Integer housNumber){
        this.companyName = companyName;
        this.location = location;
        this.housNumber = housNumber;

    }
    public static singletonPractise3 getInstance(){
        return  instance;
    }

    public Integer getHousNumber ( ) {
        return housNumber;
    }

    public String getCompanyName ( ) {
        return companyName;
    }

    public String getLocation ( ) {
        return location;
    }
}

public class singletonPractise {
    public static void main ( String[] args ) {
        singletonPractise1 singleton = singletonPractise1.getInstance ();
        singleton.setCompanyName ( "Google" );
        singleton.setHousNumber ( 123 );
        singleton.setLocation ( "hyd" );
        singletonPractise1 singleton1 =
                singletonPractise1.getInstance ();
        singleton1.setLocation ( "bglr" );
        if (singleton == singleton1){
            System.out.println ("equal" );
        }

        if (singleton.equals ( singleton1)){
            System.out.println ("equal" );
        }

    }
}

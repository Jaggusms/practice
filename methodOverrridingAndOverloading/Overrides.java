class Animal {

    int age = 10;
    static String type = "Animal";

    void sound() {
        System.out.println("Animal sound");
    }

    static void eat() {
        System.out.println("Animal eating");
    }
}

class Dog extends Animal {

    int age = 5;
    static String type = "Dog";

    @Override
    void sound() {
        System.out.println("Dog barking");
    }

    static void eat() {
        System.out.println("Dog eating");
    }
}

public class Overrides {

    public static void main(String[] args) {

        Animal a = new Dog();
        // This is called method overriding..
        // Dynamic Method Dispatch is the mechanism Java uses to decide at runtime which overridden instance method should execute.
        System.out.println(a.age);  // won't hapen for instance variable
        System.out.println(a.type);  // won't happen for static variable

        a.sound(); // can happen for the non static methods
        a.eat();  // won't happen for static methods
    }
}
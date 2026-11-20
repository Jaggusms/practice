// | Declaration | Same file |
// |---|---|
// | Two public classes | ❌ |
// | Two public interfaces | ❌ |
// | One public class + one public interface | ❌ |
// | One public interface + multiple non-public classes | ✅ |
// | One public class + multiple non-public interfaces | ✅ |

package practise.oops_practise;

import  practise.oops_practise.travel;


class classVariabless {
    public int age;
    public classVariabless(){
    }
    public classVariabless(int age){
        this.age = age;
    }
    // public boolean setAge(int age){
    //     try{
    //         this.age = age;
    //     }
    //     catch (Exception e){
    //          this.age = 0;
    //     }
    //     finally{
    //         return  (age!=0) ? true:false;
    //     }
        
    // }

    public boolean setAge(int age){
        this.age = age;
        return (age != 0) ? true : false;
        
    }
        
    public int getAge(){
        return  age;
    }
}
class Employee {

    int age = 25;

    public void  display() {
        System.out.println(age);       // Correct
        System.out.println(this.age);  // Also correct
    }

    public void setAge(int age) {
        age = age;
    }

    public void setAge1(int age) {
        this.age = age;
    }
    public void setAge2(int value) {
        age = value;
    }
}




public class classVariables {
    public static void main(String[] args) {
        classVariabless cv = new classVariabless();
        cv.setAge(10);
        System.out.println(cv.getAge());
        Employee em = new  Employee();
        em.display();
        em.setAge(10);
        em.display();
        em.setAge1(30);
        em.display();
        em.setAge1(40);
        em.display();

        Car  travelling = new car();
    }
}



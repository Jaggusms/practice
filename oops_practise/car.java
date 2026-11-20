import javax.print.DocFlavor.STRING;

import practise.oops_practise.travel;

// | Scenario | Valid? |
// |---|---|
// | Class extends one class | ✅ |
// | Class extends two classes | ❌ |
// | Class implements one interface | ✅ |
// | Class implements multiple interfaces | ✅ |
// | Interface extends multiple interfaces | ✅ |
// | Interface implements another interface | ❌ |

public class car implements travel{
    @Overrides 
    public String whichEngineGoing(){
        return  "suziki";
    }
    public String start(){
        return  "start";
    }
    
}

class train implements travel{
    @Overrides 
    public String whichEngineGoing(){
        return  "electric";
    }
 
    public String start(){
        return  "start";
    }
    
}

class bike implements travel{
    @Overrides 
    public String whichEngineGoing(){
        return  "350";
    } 
    public String start(){
        return  "start";
    }
}



class birayani implements food {
    public static String location="hyderabad";
    @Overrides 
    public String foodName(){
        return  "DumBriyani";
    }
}

/**
 * Innercar
 */
interface journey extends travel,food {
    static double time = 10.20;
    public static  String getStatus(){
        return "we are starting";}
    public String start();
    
}

/**
 * Innercar
 */
class jouneyDetails implements journey {
    public static double time = 10.20;
    public double time2 = 10.20;
    @Overrides
    public String whichEngineGoing() {
        return "Electric";
    }

    @Overrides
    public String foodName() {
        return "Dum Biryani";
    }

    @Overrides
    public String start() {
        return "We are starting";
    }
}


class jouneyDetails2 extends jouneyDetails {
    public static double time = 11.20;
    public double time2 = 10.20;
    public 
    @Overrides
    public String whichEngineGoing() {
        return "petrol";
    }

    @Overrides
    public String foodName() {
        return "Normal  Biryani";
    }

    @Overrides
    public String start() {
        return "We are starting";
    }
}
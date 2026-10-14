import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

final  class Immutablaclass1 {
    private final Integer companyAge;
    private final  String companyNmae;

    public  Immutablaclass1 ( Integer companyAge,
                              String companyNmae ){
        this.companyAge = companyAge;
        this.companyNmae = companyNmae;
    }

    public Integer getCompanyAge ( ) {
        return companyAge;
    }

    public String getCompanyNmae ( ) {
        return companyNmae;
    }
}

final  class Immutablaclass2 {
    private final Integer companyAge;
    private final  String companyNmae;

    private   Immutablaclass2 ( Integer companyAge,
                              String companyNmae ){
        this.companyAge = companyAge;
        this.companyNmae = companyNmae;
    }
    public  static  Immutablaclass2 CreateInstance(Integer age,
                                                   String name){
        return new Immutablaclass2(age, name);
    }
    public Integer getCompanyAge ( ) {
        return companyAge;
    }

    public String getCompanyNmae ( ) {
        return companyNmae;
    }
}

// Builder Design
final  class Immutablaclass3 {
    private final Integer companyAge;
    private final  String companyNmae;

    private  Immutablaclass3 ( Builder builder ){
        this.companyAge = builder.companyage;
        this.companyNmae = builder.companyname;
    }
    public Integer getCompanyAge() {
        return companyAge;
    }

    public String getCompanyName() {
        return companyNmae;
    }
    public  static class Builder {
        private Integer companyage;
        private String companyname;
        public Builder setAge(Integer age){
            this.companyage = age;
            return this;
        }
        public Builder setName(String name){
            this.companyname = name;
            return this;
        }
        public Immutablaclass3 build(){
            return  new Immutablaclass3(this);
        }
    }
}



final class Immutablaclass4 {

    private final List<String> companyLocations;

    private Immutablaclass4(Builder builder) {
        // Defensive copy and unmodifiable list
        this.companyLocations = List.copyOf(builder.companyLocations);
    }

    public List<String> getCompanyLocations() {
        return companyLocations;
    }

    public static class Builder {

        private final List<String> companyLocations = new ArrayList<>();

        public Builder addLocation(String location) {
            this.companyLocations.add(location);
            return this;
        }

        public Builder setLocations(List<String> locations) {
            this.companyLocations.clear();
            this.companyLocations.addAll(locations);
            return this;
        }

        public Immutablaclass4 build() {
            return new Immutablaclass4(this);
        }
    }
}


final class Immutablaclass5 {

    private final Set<String> companyDepartments;

    private Immutablaclass5(Builder builder) {
        this.companyDepartments =
                Set.copyOf(builder.companyDepartments);
    }

    public Set<String> getCompanyDepartments() {
        return companyDepartments;
    }

    public static class Builder {

        private final Set<String> companyDepartments =
                new HashSet<>();

        public Builder addDepartment(String department) {
            this.companyDepartments.add(department);
            return this;
        }

        public Builder setDepartments(Set<String> departments) {
            this.companyDepartments.clear();
            this.companyDepartments.addAll(departments);
            return this;
        }

        public Immutablaclass5 build() {
            return new Immutablaclass5(this);
        }
    }
}


final class Immutablaclass6 {

    private final Map<String, String> companyDetails;

    private Immutablaclass6(Builder builder) {
        this.companyDetails = Map.copyOf(builder.companyDetails);
    }

    public Map<String, String> getCompanyDetails() {
        return companyDetails;
    }

    public static class Builder {

        private final Map<String, String> companyDetails =
                new HashMap<>();

        public Builder putDetail(String key, String value) {
            this.companyDetails.put(key, value);
            return this;
        }

        public Builder setDetails(Map<String, String> details) {
            this.companyDetails.clear();
            this.companyDetails.putAll(details);
            return this;
        }

        public Immutablaclass6 build() {
            return new Immutablaclass6(this);
        }
    }
}

public class ImmutableClassPractise {
    public static void main ( String[] args ) {
        Immutablaclass2  class2 =
                Immutablaclass2.CreateInstance ( 20, "abc" );
        System.out.println (class2.getCompanyAge () );
        Immutablaclass1  class1 =
                new Immutablaclass1 ( 20, "abc" );
        System.out.println (class1.getCompanyNmae () );
        Immutablaclass3 class3 =
                new Immutablaclass3.Builder ().setName ( "abc" ).setAge ( 12 ).build ();
        Immutablaclass4.Builder builder =
                new Immutablaclass4.Builder();

        builder.addLocation("Google");
        builder.addLocation("Hyderabad");
        builder.addLocation("India");

        // Create the first object
        Immutablaclass4 company1 = builder.build();

        System.out.println("Company 1: " + company1.getCompanyLocations());

        // Modify the builder's ArrayList
        builder.addLocation("Bengaluru");

        // Create the second object
        Immutablaclass4 company2 = builder.build();

        System.out.println("Company 1: " + company1.getCompanyLocations());
        System.out.println("Company 2: " + company2.getCompanyLocations());

        System.out.println(company1 == company2);
    }

}

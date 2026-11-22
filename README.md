# practice
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {
        @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class)
    }
)
public @interface SpringBootApplication {
}


@Target specifies where an annotation is allowed to be applied.

ElementType.TYPE doesn't allow annotations on fields.


| ElementType | Where it can be applied |
|---|---|
| TYPE | Class, interface, enum, record |
| FIELD | Variables/fields |
| METHOD | Methods |
| PARAMETER | Method parameters |
| CONSTRUCTOR | Constructors |
| LOCAL_VARIABLE | Local variables |
| TYPE_USE | Any type usage |


. @Retention(RetentionPolicy.RUNTIME)
This annotation defines how long an annotation should be available.
| Retention Policy | Meaning |
|---|---|
| SOURCE | Available only in source code; discarded during compilation |
| CLASS | Stored in `.class` files but not necessarily available through runtime reflection |
| RUNTIME | Available at runtime through reflection |


4. @Documented
This annotation controls whether your custom annotation appears in the generated Java documentation (Javadoc).


5. @Inherited
This annotation allows an annotation on a parent class to be inherited by its child classes.
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyAnnotation {
}

@MyAnnotation
class Parent {
}


class Child extends Parent {
}


public class Main {

    public static void main(String[] args) {

        System.out.println(
            Child.class.isAnnotationPresent(MyAnnotation.class)  // true
        );
    }
}




@SpringBootConfiguration
This is one of the most important annotations.

What does it do?

It indicates that the class is a Spring Boot application configuration class.

@SpringBootApplication
        |
        v
@SpringBootConfiguration
        |
        v
@Configuration

Then why do we need @SpringBootConfiguration instead of directly using @Configuration?
Because @SpringBootConfiguration is a Spring Boot-specific specialization of @Configuration.

It identifies the primary Spring Boot configuration class.







7. @EnableAutoConfiguration
This is probably the most powerful feature of Spring Boot.

Its purpose is to configure Spring beans automatically based on the dependencies available in your application and the conditions that are satisfied.

Conceptually:
@EnableAutoConfiguration
          |
          v
@Import(AutoConfigurationImportSelector.class)
          |
          v
Find available auto-configuration classes
          |
          v
Evaluate conditions
          |
          v
Register applicable configuration beans

For example:
@ConditionalOnClass(DataSource.class)

This means the associated configuration is eligible only when DataSource is available on the classpath.

Other important conditional annotations:
Annotation	Purpose
@ConditionalOnClass	Checks whether a particular class exists
@ConditionalOnMissingClass	Checks whether a particular class is absent
@ConditionalOnBean	Checks whether a particular bean exists
@ConditionalOnMissingBean	Checks whether a particular bean is absent
@ConditionalOnProperty	Checks whether a configuration property has a particular value












8. @ComponentScan
This annotation tells Spring where to search for classes annotated with Spring stereotypes.

For example:
- @Component
- @Service
- @Repository
- @Controller
- @RestController









If MyApplication is in com.example.application, default scanning starts there.

It will not automatically scan sibling packages such as com.example.controller and com.example.service.

This is a very common Spring Boot interview question.

Solution:

You can specify the base package:
@SpringBootApplication(
    scanBasePackages = "com.example"
)
public class MyApplication {
}
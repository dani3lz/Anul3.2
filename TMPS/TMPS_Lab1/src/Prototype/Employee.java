package Prototype;

import java.util.ArrayList;
import java.util.List;

public class Employee extends EmployeeAbstract {
    public static List<Employee> employees = new ArrayList<>();
    private String firstName;
    private String lastName;
    private int age;
    private String position;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public Employee copy() {
        return new Employee(this.getFirstName(), this.getLastName(), this.getAge(),  this.getPosition(), true);
    }

    public Employee(String firstName, String lastName, int age, String position, boolean copy){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.position = position;
        if(!copy) {
            employees.add(this);
        }
    }
}

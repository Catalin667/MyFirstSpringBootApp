package myFirstApp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name= "employee")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee {
    @Id
    @SequenceGenerator(
            name="employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy  = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private long employeeId;

    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String city;
    @Size(min=10,max=13)
    @Column(nullable = false)
    private String  phoneNumber;
    @Email
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate hireDate;
    @Column(nullable = false)
    private double salary;
    @Column(nullable = false)
    private String functionName;

    public Employee() {
    }

    public Employee(String lastName, String firstName, String city, String phoneNumber, String email, LocalDate hireDate, double salary, String functionName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.hireDate = hireDate;
        this.salary = salary;
        this.functionName = functionName;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.getSalary(), getSalary()) == 0 && getLastName().equals(employee.getLastName()) && getFirstName().equals(employee.getFirstName()) && getCity().equals(employee.getCity()) && getPhoneNumber().equals(employee.getPhoneNumber()) && getEmail().equals(employee.getEmail()) && getHireDate().equals(employee.getHireDate()) && getFunctionName().equals(employee.getFunctionName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastName(), getFirstName(), getCity(), getPhoneNumber(), getEmail(), getHireDate(), getSalary(), getFunctionName());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", functionName='" + functionName + '\'' +
                '}';
    }
}
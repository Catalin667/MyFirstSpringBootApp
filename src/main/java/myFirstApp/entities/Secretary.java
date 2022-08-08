package myFirstApp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="secretary")
public class Secretary extends Employee{
    @Column(nullable = false)
    private int year;

    public Secretary(){

    }

    public Secretary(String lastName, String firstName, String city, String phoneNumber, String email, LocalDate hireDate, double salary, String functionName, int year) {
        super(lastName, firstName, city, phoneNumber, email, hireDate, salary, functionName);
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Secretary)) return false;
        if (!super.equals(o)) return false;
        Secretary secretary = (Secretary) o;
        return getYear() == secretary.getYear();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getYear());
    }

    @Override
    public String toString() {
        return "Secretary{" +
                "year=" + year +
                '}';
    }
}

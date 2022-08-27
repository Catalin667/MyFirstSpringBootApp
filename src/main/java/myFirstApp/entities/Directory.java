package myFirstApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="directory")
public class Directory extends Teacher{
    @Column(nullable = false)
    private int seniority;
    @Column(nullable = false)
    private int startYear;
    @Column(nullable = false)
    private int stopYear;

    @JsonIgnore
    @OneToMany(mappedBy = "directory" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Contest>contests;

    public Directory() {

    }

    public Directory(String lastName, String firstName, String city, String phoneNumber, String email, LocalDate hireDate, double salary, String functionName, String specializations, int seniority, int startYear, int stopYear) {
        super(lastName, firstName, city, phoneNumber, email, hireDate, salary, functionName, specializations);
        this.seniority = seniority;
        this.startYear = startYear;
        this.stopYear = stopYear;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getStopYear() {
        return stopYear;
    }

    public void setStopYear(int stopYear) {
        this.stopYear = stopYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Directory)) return false;
        if (!super.equals(o)) return false;
        Directory directory = (Directory) o;
        return getSeniority() == directory.getSeniority() && getStartYear() == directory.getStartYear() && getStopYear() == directory.getStopYear();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSeniority(), getStartYear(), getStopYear());
    }

    @Override
    public String toString() {
        return "Directory{" +
                "seniority=" + seniority +
                ", startYear=" + startYear +
                ", stopYear=" + stopYear +
                '}';
    }
}
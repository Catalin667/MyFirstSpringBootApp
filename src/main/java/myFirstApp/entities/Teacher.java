package myFirstApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "teacher")
public class Teacher extends Employee {
    @Column(nullable = false)
    private String specializations;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<TeacherSubjectStudent> teacherSubjectStudents;

    @OneToOne(mappedBy = "teacher" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Hall hall;

    public Teacher(){}

    public Teacher(String lastName, String firstName, String city, String phoneNumber, String email, LocalDate hireDate, double salary, String functionName, String specializations) {
        super(lastName, firstName, city, phoneNumber, email, hireDate, salary, functionName);
        this.specializations = specializations;
    }

    public Set<TeacherSubjectStudent> getTeacherSubjectStudents() {
        return teacherSubjectStudents;
    }

    public String getSpecializations() {
        return specializations;
    }

    public void setSpecializations(String specializations) {
        this.specializations = specializations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return getSpecializations().equals(teacher.getSpecializations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpecializations());
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "specializations='" + specializations + '\'' +
                '}';
    }
}

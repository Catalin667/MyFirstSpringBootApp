package myFirstApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="student")
public class Student {
    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy  = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private long studentId;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String city;
    @Size(min=10,max=10)
    @Column(nullable = false)
    private String phone;
    @Email
    @Column(nullable = false,unique = true)
    private String email;
    private String occupation;

    @JsonIgnore
    @OneToMany(mappedBy = "student" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<TeacherSubjectStudent> teacherSubjectStudents;

    @JsonIgnore
    @OneToMany(mappedBy = "student" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<StudentExam> studentExams;

    @JsonIgnore
    @OneToMany(mappedBy = "student" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ContestStudent>contests;

    public Student(){

    }

    public Student(String lastName, String firstName, int age, String city, String phone, String email, String occupation) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.occupation = occupation;
    }

    public long getStudentId() {
        return studentId;
    }

    public Set<TeacherSubjectStudent> getTeacherSubjectStudents() {
        return teacherSubjectStudents;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getAge() == student.getAge() && getLastName().equals(student.getLastName()) && getFirstName().equals(student.getFirstName()) && getCity().equals(student.getCity()) && getPhone().equals(student.getPhone()) && getEmail().equals(student.getEmail()) && getOccupation().equals(student.getOccupation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastName(), getFirstName(), getAge(), getCity(), getPhone(), getEmail(), getOccupation());
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", occupation='" + occupation + '\'' +
                ", teacherSubjectStudents=" + teacherSubjectStudents +
                '}';
    }
}

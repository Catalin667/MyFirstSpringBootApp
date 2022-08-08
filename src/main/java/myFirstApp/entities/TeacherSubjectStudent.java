package myFirstApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="teacher_subject_student")
public class TeacherSubjectStudent implements Serializable {

    @EmbeddedId
    private TeacherSubjectStudentId teacherSubjectStudentId;

    @Column(nullable = false)
    private int year;

//    @JsonBackReference
//    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Teacher teacher;

//    @JsonBackReference
//    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Subject subject;

//    @JsonBackReference
//    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Student student;


    public TeacherSubjectStudent() {
    }

//    public TeacherSubjectStudent(Teacher teacher, Subject subject, Student student, int year) {
//        this.teacherSubjectStudentId.setTeacherId(teacher.getEmployeeId());
//        this.teacherSubjectStudentId.setSubjectId(subject.getSubjectId());
//        this.teacherSubjectStudentId.setStudentId(student.getStudentId());
//        this.teacher = teacher;
//        this.subject=subject;
//        this.student=student;
//        this.year = year;
//    }

    public TeacherSubjectStudent(TeacherSubjectStudentId teacherSubjectStudentId, int year) {
        this.teacherSubjectStudentId = teacherSubjectStudentId;
        this.year = year;
    }

    public TeacherSubjectStudentId getTeacherSubjectStudentId() {
        return teacherSubjectStudentId;
    }

    public void setTeacherSubjectStudentId(TeacherSubjectStudentId teacherSubjectStudentId) {
        this.teacherSubjectStudentId = teacherSubjectStudentId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
        if (!(o instanceof TeacherSubjectStudent)) return false;
        TeacherSubjectStudent that = (TeacherSubjectStudent) o;
        return getYear() == that.getYear();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getYear());
    }

    @Override
    public String toString() {
        return "TeacherSubjectStudent{" +
                "teacherSubjectStudentId=" + teacherSubjectStudentId +
                ", year=" + year +
                '}';
    }
}
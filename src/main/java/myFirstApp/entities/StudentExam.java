package myFirstApp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="student_exam")
public class StudentExam {
    @EmbeddedId
    private StudentExamId studentExamId;

    @Column(nullable = false)
    private double grade;

    @Column
    private String mesage;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate examDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Student student;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Exam exam;

    public StudentExam() {
    }

    public StudentExam(StudentExamId studentExamId, double grade, LocalDate examDate) {
        this.studentExamId = studentExamId;
        this.grade = grade;
        this.examDate = examDate;
        if(this.grade>5)
            this.mesage = "PROMOVAT";
        else{
            this.mesage = "NEPROMOVAT";
        }
    }

    public StudentExamId getStudentExamId() {
        return studentExamId;
    }

    public void setStudentExamId(StudentExamId studentExamId) {
        this.studentExamId = studentExamId;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentExam that)) return false;
        return Double.compare(that.getGrade(), getGrade()) == 0 && getStudentExamId().equals(that.getStudentExamId()) && getMesage().equals(that.getMesage()) && getExamDate().equals(that.getExamDate()) && Objects.equals(getStudent(), that.getStudent()) && Objects.equals(getExam(), that.getExam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentExamId(), getGrade(), getMesage(), getExamDate());
    }

    @Override
    public String toString() {
        return "StudentExam{" +
                "studentExamId=" + studentExamId +
                ", grade=" + grade +
                ", mesage='" + mesage + '\'' +
                ", examDate=" + examDate +
                ", student=" + student +
                ", exam=" + exam +
                '}';
    }
}

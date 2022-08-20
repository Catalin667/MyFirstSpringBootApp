package myFirstApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name ="exam")
public class Exam {
    @EmbeddedId
    private ExamId examId;

    @Column(nullable = false)
    private String examName;

    @Column(nullable = false)
    private int year;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Subject subject;

    @JsonIgnore
    @OneToMany(mappedBy = "exam" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<StudentExam> studentExams;

    public Exam(){}

    public Exam(String examName, int year) {
        this.examName = examName;
        this.year = year;
    }

    public ExamId getExamId() {
        return examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
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
        if (!(o instanceof Exam exam)) return false;
        return getYear() == exam.getYear() && getExamId().equals(exam.getExamId()) && getExamName().equals(exam.getExamName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExamId(), getExamName(), getYear());
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId=" + examId +
                ", examName='" + examName + '\'' +
                ", year=" + year +
                '}';
    }
}
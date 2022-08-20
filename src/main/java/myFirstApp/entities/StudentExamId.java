package myFirstApp.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentExamId implements Serializable {
    private ExamId examId;
    private long studentId;

    public StudentExamId() {
    }

    public StudentExamId(ExamId examId, long studentId) {
        this.examId = examId;
        this.studentId = studentId;
    }

    public ExamId getExamId() {
        return examId;
    }

    public void setExamId(ExamId examId) {
        this.examId = examId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentExamId that)) return false;
        return getStudentId() == that.getStudentId() && getExamId().equals(that.getExamId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExamId(), getStudentId());
    }

    @Override
    public String toString() {
        return "StudentExamId{" +
                "examId=" + examId +
                ", studentId=" + studentId +
                '}';
    }
}

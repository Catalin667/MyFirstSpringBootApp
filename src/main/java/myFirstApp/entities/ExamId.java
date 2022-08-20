package myFirstApp.entities;

import jdk.jfr.Enabled;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExamId implements Serializable {
    private long examId;
    private long subjectId;

    public ExamId() {
    }

    public ExamId(long examId, long subjectId) {
        this.examId = examId;
        this.subjectId = subjectId;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExamId examId1)) return false;
        return getExamId() == examId1.getExamId() && getSubjectId() == examId1.getSubjectId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExamId(), getSubjectId());
    }

    @Override
    public String toString() {
        return "ExamId{" +
                "examId=" + examId +
                ", subjectId=" + subjectId +
                '}';
    }
}
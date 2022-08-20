package myFirstApp.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Embeddable
public class PeriodStudyId implements Serializable {

    private long periodStudyId;
    private long subjectId;

    public PeriodStudyId(){}

    public PeriodStudyId(long subjectId) {
        this.subjectId = subjectId;
    }

    public PeriodStudyId(long periodStudyId,long subjectId) {
        this.periodStudyId = periodStudyId;
        this.subjectId = subjectId;
    }

    public long getPeriodStudyId() {
        return periodStudyId;
    }

    public void setPeriodStudyId(long periodStudyId) {
        this.periodStudyId = periodStudyId;
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
        if (!(o instanceof PeriodStudyId that)) return false;
        return getPeriodStudyId() == that.getPeriodStudyId() && getSubjectId() == that.getSubjectId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPeriodStudyId(), getSubjectId());
    }

    @Override
    public String toString() {
        return "PeriodStudyId{" +
                "periodStudyId=" + periodStudyId +
                ", subjectId=" + subjectId +
                '}';
    }
}
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

    public void setPeriodStudyId(long id){
        this.periodStudyId=id;
    }
    public long getPeriodStudyId() {
        return periodStudyId;
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
        if (!(o instanceof PeriodStudyId)) return false;
        PeriodStudyId that = (PeriodStudyId) o;
        return getPeriodStudyId() == that.getPeriodStudyId() && getSubjectId() == that.getSubjectId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPeriodStudyId(), getSubjectId());
    }

    @Override
    public String toString() {
        return "PeriodStudingId{" +
                "periodStudyId=" + periodStudyId +
                ", subjectId=" + subjectId +
                '}';
    }
}

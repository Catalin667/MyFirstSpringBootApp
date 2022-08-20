package myFirstApp.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DegreeId implements Serializable {
    private long degreeId;
    private PeriodStudyId periodStudyId;

    public DegreeId() {
    }

    public DegreeId(PeriodStudyId periodStudyId) {
        this.periodStudyId = periodStudyId;
    }

    public DegreeId(long degreeId, PeriodStudyId periodStudyId) {
        this.degreeId = degreeId;
        this.periodStudyId = periodStudyId;
    }

    public long getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(long degreeId) {
        this.degreeId = degreeId;
    }

    public PeriodStudyId getPeriodStudyId() {
        return periodStudyId;
    }

    public void setPeriodStudyId(PeriodStudyId periodStudyId) {
        this.periodStudyId = periodStudyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DegreeId degreeId1)) return false;
        return getDegreeId() == degreeId1.getDegreeId() && getPeriodStudyId().equals(degreeId1.getPeriodStudyId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDegreeId(), getPeriodStudyId());
    }

    @Override
    public String toString() {
        return "DegreeId{" +
                "degreeId=" + degreeId +
                ", periodStudyId=" + periodStudyId +
                '}';
    }
}
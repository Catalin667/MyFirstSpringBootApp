package myFirstApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "period_study")
public class PeriodStudy implements Serializable {

    @EmbeddedId
    private PeriodStudyId periodStudyId;
    @Column(nullable=false)
    private String periodStudyName;
    @Column(nullable=false)
    private double taxPerYear;
    @Column(nullable=false)
    private int yearStudyNumber;
    @JsonIgnore
    @ManyToOne
    private Subject subject;

    public PeriodStudy() {
    }

    public PeriodStudy(PeriodStudyId periodStudyId, String periodStudyName, double taxPerYear, int yearStudyNumber) {
        this.periodStudyId = periodStudyId;
        this.periodStudyName = periodStudyName;
        this.taxPerYear = taxPerYear;
        this.yearStudyNumber = yearStudyNumber;
        this.subject.setSubjectId(periodStudyId.getSubjectId());
    }

    public void setPeriodStudyId(PeriodStudyId periodStudyId) {
        this.periodStudyId = periodStudyId;
    }

    public PeriodStudyId getPeriodStudyId() {
        return periodStudyId;
    }

    public String getPeriodStudyName() {
        return periodStudyName;
    }

    public void setPeriodStudyName(String periodStudyName) {
        this.periodStudyName = periodStudyName;
    }

    public double getTaxPerYear() {
        return taxPerYear;
    }

    public void setTaxPerYear(double taxPerYear) {
        this.taxPerYear = taxPerYear;
    }

    public int getYearStudyNumber() {
        return yearStudyNumber;
    }

    public void setYearStudyNumber(int yearStudyNumber) {
        this.yearStudyNumber = yearStudyNumber;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeriodStudy that)) return false;
        return Double.compare(that.getTaxPerYear(), getTaxPerYear()) == 0 && getYearStudyNumber() == that.getYearStudyNumber() && getPeriodStudyId().equals(that.getPeriodStudyId()) && getPeriodStudyName().equals(that.getPeriodStudyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPeriodStudyId(), getPeriodStudyName(), getTaxPerYear(), getYearStudyNumber());
    }

    @Override
    public String toString() {
        return "PeriodStudy{" +
                "periodStudyId=" + periodStudyId +
                ", periodStudyName='" + periodStudyName + '\'' +
                ", taxPerYear=" + taxPerYear +
                ", yearStudyNumber=" + yearStudyNumber +
                '}';
    }
}
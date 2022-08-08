package myFirstApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

//import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import static org.hibernate.annotations.GenerationTime.*;
@Entity
@Table(name = "period_study")
public class PeriodStudy implements Serializable {
//    @SequenceGenerator(
//            name="period_study_sequence",
//            sequenceName = "period_study_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy  = GenerationType.SEQUENCE,
//            generator = "period_study_sequence"
//    )
//    @Transient
//    @GeneratedValue(strategy = GenerationTime.INSERT)

    @Generated(GenerationTime.INSERT)
//    @SequenceGenerator(name="period_study_sequence",sequenceName = "period_study_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "period_study_sequence")
    @Column(updatable = false,unique = true)
    @Transient
    private long idHelp;
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
//    cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    private Subject subject;

    public PeriodStudy(){
    }

    public PeriodStudy(PeriodStudyId periodStudingId, String periodStudyName, int taxPerYear, int yearStudyNumber) {
        this.periodStudyId = periodStudingId;
        periodStudingId.setPeriodStudyId(idHelp);
        this.periodStudyName = periodStudyName;
        this.taxPerYear = taxPerYear;
        this.yearStudyNumber = yearStudyNumber;
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
        if (!(o instanceof PeriodStudy)) return false;
        PeriodStudy that = (PeriodStudy) o;
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
                ", subject=" + subject +
                '}';
    }
}

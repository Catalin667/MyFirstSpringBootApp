package myFirstApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="subject")
public class Subject {
    @Id
    @SequenceGenerator(
            name="subject_sequence",
            sequenceName = "subject_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy  = GenerationType.SEQUENCE,
            generator = "subject_sequence"
    )
    private long subjectId;
    @Column(nullable = false)
    private String subjectName;
    @Column(nullable = false)
    private int hoursCourseNumber;
    @Column(nullable = false)
    private int examNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "subject" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<TeacherSubjectStudent> teacherSubjectStudents;


    @OneToMany(mappedBy = "subject" )
    private Set<PeriodStudy> periodsStudy = new HashSet<>();

    public Subject() {
    }

    public Subject(String subjectName, int hoursCourseNumber, int examNumber) {
        this.subjectName = subjectName;
        this.hoursCourseNumber = hoursCourseNumber;
        this.examNumber = examNumber;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public Set<TeacherSubjectStudent> getTeacherSubjectStudents() {
        return teacherSubjectStudents;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getHoursCourseNumber() {
        return hoursCourseNumber;
    }

    public void setHoursCourseNumber(int hoursCourseNumber) {
        this.hoursCourseNumber = hoursCourseNumber;
    }

    public int getExamNumber() {
        return examNumber;
    }

    public void setExamNumber(int examNumber) {
        this.examNumber = examNumber;
    }

    public Set<PeriodStudy> getPeriodsStudy() {
        return periodsStudy;
    }

    public void setPeriodStudy(Set<PeriodStudy> periodsStudy) {
        this.periodsStudy = periodsStudy;
    }

    public void addANewPeriodStudy(PeriodStudy periodStudy){
        this.periodsStudy.add(periodStudy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject subject)) return false;
        return getSubjectId() == subject.getSubjectId() && getHoursCourseNumber() == subject.getHoursCourseNumber() && getExamNumber() == subject.getExamNumber() && getSubjectName().equals(subject.getSubjectName()) && getTeacherSubjectStudents().equals(subject.getTeacherSubjectStudents()) && Objects.equals(periodsStudy, subject.periodsStudy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubjectId(), getSubjectName(), getHoursCourseNumber(), getExamNumber(), getTeacherSubjectStudents(), periodsStudy);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", hoursCourseNumber=" + hoursCourseNumber +
                ", examNumber=" + examNumber +
                ", teacherSubjectStudents=" + teacherSubjectStudents +
                ", periodsStudy=" + periodsStudy +
                '}';
    }
}

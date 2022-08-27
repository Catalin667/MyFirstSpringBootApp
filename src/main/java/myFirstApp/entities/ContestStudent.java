package myFirstApp.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contest_student")
public class ContestStudent {

    @EmbeddedId
    private ContestStudentId contestStudentId;

    @Column(nullable=false)
    private String prize;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Student student;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Contest contest;

    public ContestStudent() {
    }

    public ContestStudent(ContestStudentId contestStudentId, String prize) {
        this.contestStudentId = contestStudentId;
        this.prize = prize;
    }

    public ContestStudentId getContestStudentId() {
        return contestStudentId;
    }

    public void setContestStudentId(ContestStudentId contestStudentId) {
        this.contestStudentId = contestStudentId;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContestStudent that)) return false;
        return getContestStudentId().equals(that.getContestStudentId()) && getPrize().equals(that.getPrize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContestStudentId(), getPrize());
    }

    @Override
    public String toString() {
        return "ContestStudent{" +
                "contestStudentId=" + contestStudentId +
                ", prize='" + prize + '\'' +
                '}';
    }
}

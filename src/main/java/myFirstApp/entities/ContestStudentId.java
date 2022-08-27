package myFirstApp.entities;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ContestStudentId implements Serializable {
    private long studentid;
    private long contestId;

    public ContestStudentId() {
    }

    public ContestStudentId(long studentid, long contestId) {
        this.studentid = studentid;
        this.contestId = contestId;
    }

    public long getStudentid() {
        return studentid;
    }

    public void setStudentid(long studentid) {
        this.studentid = studentid;
    }

    public long getContestId() {
        return contestId;
    }

    public void setContestId(long contestId) {
        this.contestId = contestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContestStudentId that)) return false;
        return getStudentid() == that.getStudentid() && getContestId() == that.getContestId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentid(), getContestId());
    }

    @Override
    public String toString() {
        return "ContestStudentId{" +
                "studentid=" + studentid +
                ", contestId=" + contestId +
                '}';
    }
}

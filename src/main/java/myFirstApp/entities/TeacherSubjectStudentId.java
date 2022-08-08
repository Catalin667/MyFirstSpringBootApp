package myFirstApp.entities;


import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeacherSubjectStudentId implements Serializable {

    private long employeeId;
    private long subjectId;
    private long studentId;

    public TeacherSubjectStudentId() {
    }

    public TeacherSubjectStudentId(long employeeId, long subjectId, long studentId) {
        this.employeeId = employeeId;
        this.subjectId = subjectId;
        this.studentId = studentId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
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
        if (!(o instanceof TeacherSubjectStudentId)) return false;
        TeacherSubjectStudentId that = (TeacherSubjectStudentId) o;
        return getEmployeeId() == that.getEmployeeId() && getSubjectId() == that.getSubjectId() && getStudentId() == that.getStudentId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(), getSubjectId(), getStudentId());
    }

    @Override
    public String toString() {
        return "TeacherSubjectStudentId{" +
                "employeeId=" + employeeId +
                ", subjectId=" + subjectId +
                ", studentId=" + studentId +
                '}';
    }
}

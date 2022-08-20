package myFirstApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="degree")
public class Degree {

    @EmbeddedId
    private DegreeId degreeId;

    @Column(nullable = false)
    private String degreeName;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Secretary secretary;

    public Degree() {
    }

    public Degree(DegreeId degreeId, String degreeName) {
        this.degreeId = degreeId;
        this.degreeName = degreeName;
    }

    public DegreeId getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(DegreeId degreeId) {
        this.degreeId = degreeId;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Degree degree)) return false;
        return getDegreeId().equals(degree.getDegreeId()) && getDegreeName().equals(degree.getDegreeName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDegreeId(), getDegreeName());
    }

    @Override
    public String toString() {
        return "Degree{" +
                "degreeId=" + degreeId +
                ", degreeName='" + degreeName + '\'' +
                '}';
    }
}
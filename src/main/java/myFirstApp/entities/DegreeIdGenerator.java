package myFirstApp.entities;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="degree_id_generator")
public class DegreeIdGenerator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public DegreeIdGenerator() {
    }

    public DegreeIdGenerator(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DegreeIdGenerator that)) return false;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "DegreeIdGenerator{" +
                "id=" + id +
                '}';
    }
}
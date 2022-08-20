package myFirstApp.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="exam_id_generator")
public class ExamIdGenerator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public ExamIdGenerator() {
    }

    public ExamIdGenerator(long id) {
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
        if (!(o instanceof ExamIdGenerator that)) return false;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "ExamIdGenerator{" +
                "id=" + id +
                '}';
    }
}
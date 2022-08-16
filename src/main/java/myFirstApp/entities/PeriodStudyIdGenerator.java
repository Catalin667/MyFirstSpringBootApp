package myFirstApp.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="period_study_generator_sequence")
public class PeriodStudyIdGenerator  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public PeriodStudyIdGenerator(){}

    public PeriodStudyIdGenerator(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeriodStudyIdGenerator that)) return false;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "PeriodStudyIdGenerator{" +
                "id=" + id +
                '}';
    }
}

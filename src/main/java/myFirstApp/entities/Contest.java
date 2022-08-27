package myFirstApp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "contest")
public class Contest {
    @Id
    @SequenceGenerator(
            name = "contest_sequence",
            sequenceName = "contest_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contest_sequence"
    )
    private long contestId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private LocalDate contestDate;

    @Column(nullable = false)
    private String city;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "employeeId")
    private Directory directory;

    @JsonIgnore
    @OneToMany(mappedBy = "contest" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ContestStudent> contests;

    @Transient
    private long directoryId;

    public Contest() {
    }

    public Contest(LocalDate contestDate, String city,long directoryId) {
        this.contestDate = contestDate;
        this.city = city;
        this.directoryId = directoryId;
    }

    public long getContestId() {
        return contestId;
    }

    public void setContestId(long contestId) {
        this.contestId = contestId;
    }

    public LocalDate getContestDate() {
        return contestDate;
    }

    public void setContestDate(LocalDate contestDate) {
        this.contestDate = contestDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public long getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(long directoryId) {
        this.directoryId = directoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contest contest)) return false;
        return getContestDate().equals(contest.getContestDate()) && getCity().equals(contest.getCity()) && getDirectory().equals(contest.getDirectory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContestDate(), getCity(), getDirectory());
    }

    @Override
    public String toString() {
        return "Contest{" +
                "contestId=" + contestId +
                ", contestDate=" + contestDate +
                ", city='" + city + '\'' +
                ", directory=" + directory +
                ", directoryId=" + directoryId +
                '}';
    }
}
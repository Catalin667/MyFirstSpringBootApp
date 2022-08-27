package myFirstApp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @SequenceGenerator(
            name="schedule_sequence",
            sequenceName = "schedule_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy  = GenerationType.SEQUENCE,
            generator = "schedule_sequence"
    )
    private long scheduleId;


    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "KK:mm a")
    @Column(nullable = false)
    private LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "KK:mm a")
    @Column(nullable = false)
    private LocalTime stopTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    private LocalDate schoolYear;

    @JsonIgnore
    @OneToMany(mappedBy = "schedule" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Hall>halls;

    public Schedule() {
    }

    public Schedule(LocalTime startTime, LocalTime stopTime, LocalDate schoolYear) {
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.schoolYear = schoolYear;
    }

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalTime stopTime) {
        this.stopTime = stopTime;
    }

    public LocalDate getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(LocalDate schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Set<Hall> getHalls() {
        return halls;
    }

    public void setHalls(Set<Hall> halls) {
        this.halls = halls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule schedule)) return false;
        return getStartTime().equals(schedule.getStartTime()) && getStopTime().equals(schedule.getStopTime()) && getSchoolYear().equals(schedule.getSchoolYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartTime(), getStopTime(), getSchoolYear());
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                ", schoolYear=" + schoolYear +
                ", halls=" + halls +
                '}';
    }
}

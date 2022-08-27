package myFirstApp.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hall")
public class Hall {
    @Id
    @SequenceGenerator(
            name="hall_sequence",
            sequenceName = "hall_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy  = GenerationType.SEQUENCE,
            generator = "hall_sequence"
    )
    private long hallId;

    @Column(nullable = false)
    private String nameHall;

    @Column(nullable = false)
    private int floor;

    @Transient
    private long teacherId;

    @Transient
    private long scheduleId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId")
    private Teacher teacher;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Schedule schedule;

    public Hall(){}

    public Hall(String nameHall, int floor,long teacherId,long scheduleId) {
        this.nameHall = nameHall;
        this.floor = floor;
        this.teacherId = teacherId;
        this.scheduleId = scheduleId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public long getHallId() {
        return hallId;
    }

    public void setHallId(long hallId) {
        this.hallId = hallId;
    }

    public String getNameHall() {
        return nameHall;
    }

    public void setNameHall(String nameHall) {
        this.nameHall = nameHall;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hall hall)) return false;
        return getHallId() == hall.getHallId() && getFloor() == hall.getFloor() && getNameHall().equals(hall.getNameHall());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHallId(), getNameHall(), getFloor());
    }

    @Override
    public String toString() {
        return "Hall{" +
                "hallId=" + hallId +
                ", nameHall='" + nameHall + '\'' +
                ", floor=" + floor +
                ", teacher=" + teacher +
                '}';
    }
}
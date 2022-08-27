package myFirstApp.services;

import myFirstApp.entities.Hall;
import myFirstApp.entities.Schedule;
import myFirstApp.entities.Teacher;
import myFirstApp.repositories.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HallServices {
    private final HallRepository hallRepository;
    @Autowired
    public HallServices(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    public void registerAHall(Hall hall) {
        hallRepository.save(hall);
    }

    public void updateAHall(long hallId, String nameHall, String floor, Teacher teacher, Schedule schedule) {
        Hall hall = getAHallById(hallId);

        if(nameHall!=null && nameHall.length()>0 && !nameHall.equals(hall.getNameHall())){
            hall.setNameHall(nameHall);
        }

        if(floor!=null && floor.length()>0){
            int floorInt = Integer.parseInt(floor);
            if(floorInt>0 && floorInt!=hall.getFloor()){
                hall.setFloor(floorInt);
            }
        }

        if(teacher!=null && !(hall.getTeacher()).equals(teacher)){
            hall.setTeacher(teacher);
            hall.setTeacherId(teacher.getEmployeeId());
        }

        if(schedule!=null && !(hall.getSchedule()).equals(schedule)){
            hall.setSchedule(schedule);
            hall.setScheduleId(schedule.getScheduleId());
        }
    }

    public void deleteAHall(long hallId){
        if(checkIfExistsAHallById(hallId)) {
            hallRepository.deleteById(hallId);
        }else {
            throw new IllegalStateException("Hall with id " + hallId + " does not exist.");
        }
    }
    public boolean checkIfExistsAHallById(long hallId){
        return hallRepository.existsById(hallId);
    }
    @Transactional
    public Hall getAHallById(long hallId){
        return hallRepository.findById(hallId)
                .orElseThrow(()->new IllegalStateException("Hall with id "+ hallId + " does not exist."));
    }
}
package myFirstApp.services;

import myFirstApp.entities.Hall;
import myFirstApp.repositories.HallRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HallServices {
    private final HallRepository hallRepository;

    public HallServices(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    public void registerAHall(Hall hall) {
        hallRepository.save(hall);
    }

    public void updateAHall(long hallId, String nameHall, String floor, String teacherId) {
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
    }

    public boolean checkIfExistsAHallById(long hallId){
        return hallRepository.existsById(hallId);
    }
    @Transactional
    public Hall getAHallById(long hallId){
        return hallRepository.findById(hallId)
                .orElseThrow(()->new IllegalStateException("Hall with id "+ hallId + " does not exists."));
    }
}
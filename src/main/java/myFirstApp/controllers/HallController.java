package myFirstApp.controllers;

import myFirstApp.entities.Hall;
import myFirstApp.entities.Teacher;
import myFirstApp.services.HallServices;
import myFirstApp.services.TeacherServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="api/hall")
public class HallController {
    private final HallServices hallServices;

    private final TeacherServices teacherServices;

    public HallController(HallServices hallServices, TeacherServices teacherServices) {
        this.hallServices = hallServices;
        this.teacherServices = teacherServices;
    }

    @RequestMapping
    public List<Hall> getAllHalls(){
        return hallServices.getAllHalls();
    }

    @PostMapping
    public void registerAHall(@RequestBody Hall hall){
        long teacherId = hall.getTeacherId();
        Teacher  teacher = teacherServices.getTeacherById(teacherId);
        hall.setTeacher(teacher);
        hallServices.registerAHall(hall);
    }
//    String nameHall, int floor,long teacherId
    @PutMapping(path="{hallId}")
    public void updateAHall(@PathVariable ("hallId") long hallId,
                            @RequestParam (required = false)String nameHall,
                            @RequestParam(required = false)String floor,
                            @RequestParam(required = false)String teacherId){
        hallServices.updateAHall(hallId,nameHall,floor,teacherId);
    }
}
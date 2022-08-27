package myFirstApp.controllers;

import myFirstApp.entities.Hall;
import myFirstApp.entities.Schedule;
import myFirstApp.entities.Teacher;
import myFirstApp.services.HallServices;
import myFirstApp.services.ScheduleServices;
import myFirstApp.services.TeacherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="api/hall")
public class HallController {
    private final HallServices hallServices;

    private final TeacherServices teacherServices;

    private final ScheduleServices  scheduleServices;
    @Autowired
    public HallController(HallServices hallServices, TeacherServices teacherServices, ScheduleServices scheduleServices) {
        this.hallServices = hallServices;
        this.teacherServices = teacherServices;
        this.scheduleServices = scheduleServices;
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

    @PutMapping(path="{hallId}")
    public void updateAHall(@PathVariable ("hallId") long hallId,
                            @RequestParam (required = false)String nameHall,
                            @RequestParam(required = false)String floor,
                            @RequestParam(required = false)String teacherId,
                            @RequestParam (required = false)String scheduleId){
        if((teacherId!=null && teacherId.length()>0)||(scheduleId!=null && scheduleId.length()>0)){
            Teacher teacher = null;
            Schedule schedule = null;
            int teacherIdInt = -1,scheduleIdInt = -1;
            if(teacherId!=null){
            teacherIdInt = Integer.parseInt(teacherId);}
            if(scheduleId!=null) {
                scheduleIdInt = Integer.parseInt(scheduleId);}

            if(teacherIdInt!=-1 && teacherServices.checkIfExistsById(teacherIdInt)){
                teacher = teacherServices.getTeacherById(teacherIdInt);
            }

            if(scheduleIdInt!=-1 && scheduleServices.checkIfExistsAScheduleById(scheduleIdInt)){
                schedule = scheduleServices.getScheduleById(scheduleIdInt);
            }
            hallServices.updateAHall(hallId,nameHall,floor,teacher,schedule);
        }
        hallServices.updateAHall(hallId,nameHall,floor,null,null);
    }

    @DeleteMapping(path="{hallId}")
    public void deleteAHall(@PathVariable ("hallId") long hallId){
        hallServices.deleteAHall(hallId);
    }
}
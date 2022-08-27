package myFirstApp.controllers;

import myFirstApp.entities.Schedule;
import myFirstApp.services.ScheduleServices;
import myFirstApp.services.TeacherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/schedule")
public class ScheduleController {

    private final ScheduleServices scheduleServices;
    @Autowired
    public ScheduleController(ScheduleServices scheduleServices) {
        this.scheduleServices = scheduleServices;
    }

    @GetMapping
    public List<Schedule> getAllSchedules (){
        return scheduleServices.getAllSchedules();
    }

    @PostMapping
    public void registerANewSchedule(@RequestBody Schedule schedule){
        scheduleServices.registerANewSchedule(schedule);
    }

    @PutMapping(path="{scheduleId}")
    public void updateASchedule(@PathVariable("scheduleId")long scheduleId,
                                @RequestParam (required = false)String startTime,
                                @RequestParam (required = false)String stopTime,
                                @RequestParam (required = false)String schoolYear){
        scheduleServices.updateASchedule(scheduleId,startTime,stopTime,schoolYear);
    }

    @DeleteMapping(path="{scheduleId}")
    public void deleteASchedule(@PathVariable("scheduleId")long scheduleId){
        scheduleServices.deleteASchedule(scheduleId);
    }
}
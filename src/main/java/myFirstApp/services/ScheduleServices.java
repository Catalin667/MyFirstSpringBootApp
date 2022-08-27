package myFirstApp.services;

import myFirstApp.entities.Schedule;
import myFirstApp.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ScheduleServices {

    private final ScheduleRepository scheduleRepository;
    @Autowired
    public ScheduleServices(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public void registerANewSchedule(Schedule schedule) {
        List<Schedule> schedules = getAllSchedules();
        for(Schedule s:schedules){
            if(s.equals(schedule)){
                throw new IllegalStateException("This schedule already exists.");
            }
        }
        scheduleRepository.save(schedule);
    }


    public void updateASchedule(long scheduleId, String startTime, String stopTime, String schoolYear) {
        Schedule schedule = getScheduleById(scheduleId);

        if(startTime!=null && startTime.length()>0){
            LocalTime startTimeJava = LocalTime.parse(startTime);
            if(!startTimeJava.equals(schedule.getStartTime())){
                schedule.setStartTime(startTimeJava);
            }
        }

        if(stopTime!=null && stopTime.length()>0){
            LocalTime stopTimeJava = LocalTime.parse(stopTime);
            if(!stopTimeJava.equals(schedule.getStopTime())){
                schedule.setStartTime(stopTimeJava);
            }
        }

        if(schoolYear!=null && schoolYear.length()>0){
            LocalDate schoolYearJava = LocalDate.parse(schoolYear);
            if(!schoolYearJava.equals(schedule.getSchoolYear())){
                schedule.setSchoolYear(schoolYearJava);
            }
        }
    }

    @Transactional
    public Schedule getScheduleById(long scheduleId){
      return scheduleRepository.findById(scheduleId)
                .orElseThrow(()->new IllegalStateException("Schedule with id "+ scheduleId + " does not exist."));
    }

    public void deleteASchedule(long scheduleId) {
        boolean bool = checkIfExistsAScheduleById(scheduleId);
        if(bool){
            scheduleRepository.deleteById(scheduleId);
        }else{
            throw new IllegalStateException("This schedule does not exist.");
        }
    }

    public boolean checkIfExistsAScheduleById(long scheduleId){
        return scheduleRepository.existsById(scheduleId);
    }

}

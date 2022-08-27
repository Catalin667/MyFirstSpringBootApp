package myFirstApp.controllers;

import myFirstApp.entities.Contest;
import myFirstApp.entities.ContestStudent;
import myFirstApp.entities.ContestStudentId;
import myFirstApp.entities.Student;
import myFirstApp.services.ContestServices;
import myFirstApp.services.ContestStudentServices;
import myFirstApp.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/contest_student")
public class ContestStudentController {

    private final ContestStudentServices contestStudentServices;

    private final StudentServices studentServices;

    private final ContestServices contestServices;
    @Autowired
    public ContestStudentController(ContestStudentServices contestStudentServices, StudentServices studentServices, ContestServices contestServices) {
        this.contestStudentServices = contestStudentServices;
        this.studentServices = studentServices;
        this.contestServices = contestServices;
    }

    @GetMapping
    public List<ContestStudent> getAllContestStudent(){
        return contestStudentServices.getAllContestStudent();
    }

    @PutMapping
    public void registerAContestStudent(@RequestBody ContestStudent contestStudent){
         long contestId = contestStudent.getContestStudentId().getContestId();
         long studentId = contestStudent.getContestStudentId().getStudentid();
         Student student = studentServices.getStudentById(studentId);
         Contest contest = contestServices.getContestById(contestId);
         contestStudent.setContest(contest);
         contestStudent.setStudent(student);
         contestStudentServices.registerAContestStudent(contestStudent);
    }

    @PostMapping(path="{contestId}/{studentId}")
    public void updateAContestStudent(@PathVariable ("contestId")long contestId,
                                      @PathVariable ("studentId")long studentId,
                                      @RequestParam(required = false)String prize){
        ContestStudentId contestStudentId = new ContestStudentId(contestId,studentId);
        contestStudentServices.updateAContestStudent(contestStudentId,prize);
    }

    @DeleteMapping(path="{contestId}/{studentId}")
    public void deleteAContestStudent(@PathVariable ("contestId")long contestId,
                                      @PathVariable ("studentId")long studentId){
        ContestStudentId contestStudentId = new ContestStudentId(contestId,studentId);
        contestStudentServices.deleteAContestStudent(contestStudentId);
    }

}
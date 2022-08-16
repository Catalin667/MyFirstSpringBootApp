package myFirstApp.controllers;

import myFirstApp.entities.Teacher;
import myFirstApp.services.TeacherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/teacher")
public class TeacherController {
    private final TeacherServices teacherServices;
    @Autowired
    public TeacherController(TeacherServices teacherServices) {
        this.teacherServices = teacherServices;
    }

    @GetMapping
    public List<Teacher> getAllTeachers(){
        return teacherServices.getAllTeachers();
    }

    @PostMapping
    public void registerANewTeacher(@RequestBody Teacher teacher){
        teacherServices.addNewTeacher(teacher);
    }

    @PutMapping(path="{employeeId}")
    public void updateATeacher(
            @PathVariable ("employeeId") long teacherId,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String city,
            @RequestParam(required = false)String phoneNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String hireDate,
            @RequestParam(required = false) String salary,
            @RequestParam(required = false) String specializations
    ){
        teacherServices.updateATeacher(teacherId,lastName,firstName,city,phoneNumber,email,hireDate,salary,specializations);
    }

    @DeleteMapping(path="{teacherId}")
    public void deleteATeacher(@PathVariable ("teacherId") long teacherId){
        teacherServices.deleteATeacher(teacherId);
    }

}

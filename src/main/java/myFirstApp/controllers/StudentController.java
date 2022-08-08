package myFirstApp.controllers;

import myFirstApp.entities.Student;
import myFirstApp.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/student")
public class StudentController {
    private final StudentServices studentServices;
    @Autowired
    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentServices.getAllStudents();
    }

    @PostMapping
    public void registerANewStudent(@RequestBody Student student){
        studentServices.registerANewStudent(student);
    }

    @PutMapping(path ="{studentId}")
    public void updateStudent(
            @PathVariable ("studentId") long studentId,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String ocupation,
            @RequestParam(required = false) String phoneNumber
    ){
        studentServices.updateStudent(studentId,lastName,firstName,city,email,ocupation,phoneNumber);
    }

    @DeleteMapping(path ="{studentId}")
    public void deleteAStudent(@PathVariable ("studentId") long studentId){
        studentServices.deleteStudent(studentId);
    }
}

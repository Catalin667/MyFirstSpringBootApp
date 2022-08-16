package myFirstApp.controllers;

import myFirstApp.entities.TeacherSubjectStudent;
import myFirstApp.entities.TeacherSubjectStudentId;
import myFirstApp.services.TeacherSubjectStudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/teacher_subject_student")
public class TeacherSubjectStudentController {
    private final TeacherSubjectStudentServices teacherSubjectStudentServices;
    @Autowired
    public TeacherSubjectStudentController(TeacherSubjectStudentServices teacherSubjectStudentServices) {
        this.teacherSubjectStudentServices = teacherSubjectStudentServices;
    }

    @GetMapping
    public List<TeacherSubjectStudent> getAll(){
        return teacherSubjectStudentServices.getAll();
    }

    @PostMapping
    public void createTeacherSubjectStudent(@RequestBody TeacherSubjectStudent teacherSubjectStudent){
        teacherSubjectStudentServices.createTeacherSubjectStudent(teacherSubjectStudent);
    }

    @PutMapping(path={"{teacherId}/{subjectId}/{studentId}"})
    public void update(
                       @PathVariable ("teacherId") long teacherId,
                       @PathVariable ("subjectId") long subjectId,
                       @PathVariable ("studentId") long studentId,
                       @RequestParam(required = false) String year)
    {
        TeacherSubjectStudentId tss = new TeacherSubjectStudentId(teacherId,subjectId,studentId);
        teacherSubjectStudentServices.update(tss,year);
    }

    @DeleteMapping(path={"{teacherId}/{subjectId}/{studentId}"})
    public void delete(@PathVariable ("teacherId") long teacherId,
                       @PathVariable ("subjectId") long subjectId,
                       @PathVariable ("studentId") long studentId){
        TeacherSubjectStudentId tss = new TeacherSubjectStudentId(teacherId,subjectId,studentId);
        teacherSubjectStudentServices.delete(tss);
    }
}

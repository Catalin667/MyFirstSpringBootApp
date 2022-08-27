package myFirstApp.controllers;

import myFirstApp.entities.*;
import myFirstApp.services.ExamServices;
import myFirstApp.services.StudentExamServices;
import myFirstApp.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/student_exam")
public class StudentExamController {
    private final StudentExamServices studentExamServices;

    private final ExamServices examServices;

    private final StudentServices studentServices;
    @Autowired
    public StudentExamController(StudentExamServices studentExamServices, ExamServices examServices, StudentServices studentServices) {
        this.studentExamServices = studentExamServices;
        this.examServices = examServices;

        this.studentServices = studentServices;
    }

    @GetMapping
    public List<StudentExam> getAllExamsTaken(){
        return studentExamServices.getAllExamsTaken();
    }

    @PostMapping
    public void registerAExamTaken(@RequestBody StudentExam studentExam){
        long studentId = studentExam.getStudentExamId().getStudentId();
        ExamId examId = studentExam.getExam().getExamId();

        Student student = studentServices.getStudentById(studentId);
        Exam exam = examServices.getExamById(examId);

        studentExam.setStudent(student);
        studentExam.setExam(exam);

        studentExamServices.registerAExamTaken(studentExam);
    }

    @PutMapping(path="{examId}/{subjectId}/{studentId}")
    public void updateAExamTaken(@PathVariable ("examId") long examId,
                                   @PathVariable ("subjectId")long subjectId,
                                   @PathVariable ("studentId")long studentId,
                                   @RequestParam (required = false)String grade,
                                   @RequestParam (required = false)String examDate){
        ExamId examId1= new ExamId(examId,subjectId);
        StudentExamId studentExamId = new StudentExamId(examId1,studentId);
        studentExamServices.updateAExamTaken(studentExamId,grade,examDate);
    }

    @DeleteMapping(path="{examId}/{subjectId}/{studentId}")
    public void deleteAExamTaken(@PathVariable ("examId") long examId,
                                 @PathVariable ("subjectId")long subjectId,
                                 @PathVariable ("studentId")long studentId){
        ExamId examId1= new ExamId(examId,subjectId);
        StudentExamId studentExamId = new StudentExamId(examId1,studentId);
        studentExamServices.deleteAExamTaken(studentExamId);
    }
}
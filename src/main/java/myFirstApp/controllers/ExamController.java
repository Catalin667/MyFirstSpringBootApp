package myFirstApp.controllers;

import myFirstApp.entities.Exam;
import myFirstApp.entities.ExamId;
import myFirstApp.entities.ExamIdGenerator;
import myFirstApp.services.ExamIdGeneratorServices;
import myFirstApp.services.ExamServices;
import myFirstApp.services.SubjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/exam")
public class ExamController {

    private final ExamServices examServices;

    private final SubjectServices subjectServices;

    private final ExamIdGeneratorServices examIdGeneratorServices;

    @Autowired
    public ExamController(ExamServices examServices, SubjectServices subjectServices, ExamIdGeneratorServices examIdGeneratorServices) {
        this.examServices = examServices;
        this.subjectServices = subjectServices;
        this.examIdGeneratorServices = examIdGeneratorServices;
    }

    @GetMapping
    public List<Exam> getAllExams(){
        return examServices.getAllExams();
    }

    @PostMapping
    public void registerANewExam(@RequestBody Exam exam){
        long subjectId = exam.getExamId().getSubjectId();
        if(subjectServices.checkIfExistsById(subjectId)) {
            ExamIdGenerator examIdGenerator = new ExamIdGenerator();
            examIdGeneratorServices.generateANewValue(examIdGenerator);
            examServices.registerANewExam(exam);
        }else{
            throw new IllegalStateException("Subject with this id does not exists.");
        }
    }

    @PutMapping(path="{examId}/{subjectId}")
    public void updateAnExam(@PathVariable ("examId") long examId,
                             @PathVariable ("subjectId") long subjectId,
                             @RequestParam (required = false)String examName,
                             @RequestParam (required = false) String year){
        ExamId examFullId =  new ExamId(examId,subjectId);
        examServices.updateAnExam(examFullId,examName,year);
    }

    @DeleteMapping(path="{examId}/{subjectId}")
    public void deleteAnExam(@PathVariable ("examId") long examId,
                             @PathVariable ("subjectId") long subjectId){
        ExamId examFullId =  new ExamId(examId,subjectId);
        examServices.deleteAnExam(examFullId);
    }
}

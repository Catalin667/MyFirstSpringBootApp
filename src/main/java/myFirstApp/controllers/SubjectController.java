package myFirstApp.controllers;

import myFirstApp.entities.PeriodStudy;
import myFirstApp.entities.Subject;
import myFirstApp.services.PeriodStudyServices;
import myFirstApp.services.SubjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path="api/subject")
public class SubjectController {
    private final SubjectServices subjectServices;

    private final PeriodStudyServices periodStudyServices;
    @Autowired
    public SubjectController(SubjectServices subjectServices, PeriodStudyServices periodStudyServices) {
        this.subjectServices = subjectServices;
        this.periodStudyServices = periodStudyServices;
    }

    @GetMapping
    public List<Subject> getAllSubjects(){
        List<Subject> subjects = subjectServices.getAllSubjects();
        for(Subject s:subjects){
           Set<PeriodStudy>periodsStudy =  periodStudyServices.getPeriodStudyById(s.getSubjectId());
            s.setPeriodStudy(periodsStudy);
        }
        return subjects;
    }
    @PostMapping
    public void registerANewSubject(@RequestBody Subject subject){
        subjectServices.addNewSubject(subject);
    }

    @PutMapping(path="{subjectId}")
    public void updateSubject(
            @PathVariable ("subjectId") long subjectId,
            @RequestParam(required = false) String subjectName,
            @RequestParam(required = false) String hoursCourseNumber,
            @RequestParam(required = false)String examsNumber
    ){
        subjectServices.updateASubject(subjectId,subjectName,hoursCourseNumber,examsNumber);
    }

    @DeleteMapping(path ="{subjectId}")
    public void deleteAStubject(@PathVariable ("subjectId") long subjectId){
        subjectServices.deleteSubject(subjectId);
    }
}

package myFirstApp.controllers;

import myFirstApp.entities.Subject;
import myFirstApp.services.SubjectServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/subject")
public class SubjectController {
    private final SubjectServices subjectServices;

    public SubjectController(SubjectServices subjectServices) {
        this.subjectServices = subjectServices;
    }

    @GetMapping
    public List<Subject> getAllSubjects(){
        return subjectServices.getAllSubjects();
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

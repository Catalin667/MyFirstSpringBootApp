package myFirstApp.controllers;

import myFirstApp.entities.Degree;
import myFirstApp.entities.DegreeId;
import myFirstApp.entities.DegreeIdGenerator;
import myFirstApp.entities.PeriodStudyId;
import myFirstApp.services.DegreeIdGeneratorServices;
import myFirstApp.services.DegreeServices;
import myFirstApp.services.PeriodStudyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/degree")
public class DegreeController {
    private final DegreeServices degreeServices;

    private final DegreeIdGeneratorServices degreeIdGeneratorServices;

    private final PeriodStudyServices periodStudyServices;

    @Autowired
    public DegreeController(DegreeServices degreeServices, DegreeIdGeneratorServices degreeIdGeneratorServices, PeriodStudyServices periodStudyServices) {
        this.degreeServices = degreeServices;
        this.degreeIdGeneratorServices = degreeIdGeneratorServices;
        this.periodStudyServices = periodStudyServices;
    }

    @GetMapping
    public List<Degree> getAllDegrees(){
        return degreeServices.getAllDegrees();
    }

    @PostMapping
    public void registerANewDegree(@RequestBody Degree degree){
        PeriodStudyId periodStudyId = degree.getDegreeId().getPeriodStudyId();
        if(periodStudyServices.checkIfExistsById(periodStudyId)) {
            DegreeIdGenerator degreeIdGenerator = new DegreeIdGenerator();
            degreeIdGeneratorServices.generateANewValue(degreeIdGenerator);
            degreeServices.registerANewDegree(degree);
        }else{
            throw new IllegalStateException("This id does not exist.");
        }
    }

    @PutMapping(path={"{degreeId}/{periodStudyId}/{subjectId}"})
    public void updateADegree(@PathVariable("degreeId") long degreeId,
                              @PathVariable ("periodStudyId") long periodStudyId,
                              @PathVariable ("subjectId") long subjectId,
                              @RequestParam (required = false) String degreeName
        ){
        PeriodStudyId periodStudyId1 = new PeriodStudyId(periodStudyId,subjectId);
        DegreeId degreeId1 = new DegreeId(degreeId,periodStudyId1);
        degreeServices.updateADegree(degreeId1,degreeName);
    }

    @DeleteMapping(path={"{degreeId}/{periodStudyId}/{subjectId}"})
    public void deleteADegree(@PathVariable("degreeId") long degreeId,
                              @PathVariable ("periodStudyId") long periodStudyId,
                              @PathVariable ("subjectId") long subjectId){
        PeriodStudyId periodStudyId1 = new PeriodStudyId(periodStudyId,subjectId);
        DegreeId degreeId1 = new DegreeId(degreeId,periodStudyId1);
        degreeServices.deleteADegree(degreeId1);
    }
}

package myFirstApp.controllers;

import myFirstApp.entities.PeriodStudy;
import myFirstApp.entities.PeriodStudyId;
import myFirstApp.services.PeriodStudyServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/period_study")
public class PeriodStudyController {

    private final PeriodStudyServices periodStudyServices;

    public PeriodStudyController(PeriodStudyServices periodStudyServices) {
        this.periodStudyServices = periodStudyServices;
    }


    @GetMapping
    public List<PeriodStudy> getAllPeriodsStudy(){
        return periodStudyServices.getAllPeriodsStudy();
    }

    @PostMapping
    public void registerANewPeriodStudy(@RequestBody PeriodStudy periodStudy){
        periodStudyServices.registerANewPeriodStudy(periodStudy);
    }

    @PutMapping(path={"{periodStudyId}/{subjectId}"})
    public void updateANewPeriodStudy(
            @PathVariable ("periodStudyId") long periodStudyId,
            @PathVariable ("subjectId") long subjectId,
            @RequestParam(required = false) String periodStudyingName,
            @RequestParam (required = false) String taxPerYear,
            @RequestParam (required = false) String yearStudyingNumber
    ){
        PeriodStudyId periodStudyId1 = new PeriodStudyId(periodStudyId,subjectId);
        periodStudyServices.updateANewPeriodStudy(periodStudyId1,periodStudyingName,taxPerYear,yearStudyingNumber);
    }

    @DeleteMapping(path={"{periodStudyId}/{subjectId}"})
    public void deleteAPeriodStudy(@PathVariable ("periodStudyId") long periodStudyId,
                                   @PathVariable ("subjectId") long subjectId){
        PeriodStudyId periodStudyId1 = new PeriodStudyId(periodStudyId,subjectId);
        periodStudyServices.deleteAPeriodStudy(periodStudyId1);
    }

}

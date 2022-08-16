package myFirstApp.controllers;

import myFirstApp.entities.PeriodStudy;
import myFirstApp.entities.PeriodStudyId;
import myFirstApp.entities.PeriodStudyIdGenerator;
import myFirstApp.services.PeriodStudyIdGeneratorServices;
import myFirstApp.services.PeriodStudyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/period_study")
public class PeriodStudyController {
      private final PeriodStudyServices periodStudyServices;

      private final PeriodStudyIdGeneratorServices periodStudyIdGeneratorServices;
    @Autowired
    public PeriodStudyController(PeriodStudyServices periodStudyServices, PeriodStudyIdGeneratorServices periodStudyIdGeneratorServices) {
        this.periodStudyServices = periodStudyServices;
        this.periodStudyIdGeneratorServices = periodStudyIdGeneratorServices;
    }

    @GetMapping
    public List<PeriodStudy> getAllPeriodsStudy(){
        return periodStudyServices.getAllPeriodsStudy();
    }

    @PostMapping
        public void registerANewPeriodStudy(@RequestBody PeriodStudy periodStudy){
             periodStudyIdGeneratorServices.generateANewValue();
             List<PeriodStudyIdGenerator> periodStudyIdGenerators =  periodStudyIdGeneratorServices.getAllgeneratedValues();
             long periodStudyId = periodStudyIdGenerators.get(periodStudyIdGenerators.size()-1).getId();
             PeriodStudyId periodStudyId1 = periodStudy.getPeriodStudyId();
             periodStudyId1.setPeriodStudyId(periodStudyId);
             periodStudy.setPeriodStudyId(periodStudyId1);
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
        PeriodStudyId periodStudyId1 = new PeriodStudyId();
        periodStudyId1.setPeriodStudyId(periodStudyId);
        periodStudyId1.setSubjectId(subjectId);
        periodStudyServices.updateANewPeriodStudy(periodStudyId1,periodStudyingName,taxPerYear,yearStudyingNumber);
    }

    @DeleteMapping(path={"{periodStudyId}/{subjectId}"})
    public void deleteAPeriodStudy(@PathVariable ("periodStudyId") long periodStudyId,
                                   @PathVariable ("subjectId") long subjectId){
        PeriodStudyId periodStudyId1 = new PeriodStudyId();
        periodStudyId1.setPeriodStudyId(periodStudyId);
        periodStudyId1.setSubjectId(subjectId);
        periodStudyServices.deleteAPeriodStudy(periodStudyId1);
    }
}

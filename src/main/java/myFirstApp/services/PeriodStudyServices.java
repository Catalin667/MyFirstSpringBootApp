package myFirstApp.services;

import myFirstApp.entities.PeriodStudy;
import myFirstApp.entities.PeriodStudyId;
import myFirstApp.entities.Subject;
import myFirstApp.repositories.PeriodStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PeriodStudyServices {

    private final PeriodStudyRepository periodStudyRepository;

    private final SubjectServices subjectServices;
    @Autowired
    public PeriodStudyServices(PeriodStudyRepository periodStudyRepository, SubjectServices subjectServices) {
        this.periodStudyRepository = periodStudyRepository;
        this.subjectServices = subjectServices;
    }

    public List<PeriodStudy> getAllPeriodsStudy() {
        return periodStudyRepository.findAll();
    }

    public void registerANewPeriodStudy(PeriodStudy periodStudy) {
        List<PeriodStudy> periodsStudy = getAllPeriodsStudy();

        for(PeriodStudy p : periodsStudy){
            if(p.equals(periodStudy)){
                throw new IllegalStateException("This period already exists for this subject.");
            }
        }
        if(subjectServices.checkIfExistsById(periodStudy.getPeriodStudyId().getSubjectId())) {
            periodStudyRepository.save(periodStudy);
            subjectServices.addNewPeriodStudy(periodStudy.getPeriodStudyId().getSubjectId(),periodStudy);
             Subject subject = subjectServices.getSubjectById(periodStudy.getPeriodStudyId().getSubjectId());
             periodStudy.setSubject(subject);
        }
    }

    public Set<PeriodStudy> getPeriodStudyById(long subjectId){
        List<PeriodStudy> periodsStudy = getAllPeriodsStudy();
        Set<PeriodStudy> periodsStudyForThisSubject = new HashSet<>();
        for(PeriodStudy p:periodsStudy){
            if(p.getPeriodStudyId().getSubjectId() == subjectId){
                periodsStudyForThisSubject.add(p);
            }
        }
        return periodsStudyForThisSubject;
    }

    @Transactional
    public void updateANewPeriodStudy(PeriodStudyId periodStudyId, String periodStudyName, String taxPerYear, String yearStudyingNumber) {
        PeriodStudy periodStudy = periodStudyRepository.findById(periodStudyId)
                .orElseThrow(()->new IllegalStateException("Student with id "+ periodStudyId + " does not exist."));

        if(periodStudyName!=null && periodStudyName.length()>0 && periodStudyName.equals(periodStudy.getPeriodStudyName())){
            periodStudy.setPeriodStudyName(periodStudyName);
        }

        if(taxPerYear!=null && taxPerYear.length()>0){
            int taxPerYearInt = Integer.parseInt(taxPerYear);
            if(taxPerYearInt>0){
                periodStudy.setTaxPerYear(taxPerYearInt);
            }
        }

        if(yearStudyingNumber!=null && yearStudyingNumber.length()>0){
            int yearStudyingNumberInt = Integer.parseInt(yearStudyingNumber);
            if(yearStudyingNumberInt>0){
                periodStudy.setYearStudyNumber(yearStudyingNumberInt);
            }
        }
    }

    public void deleteAPeriodStudy(PeriodStudyId periodStudyId1) {
        boolean bool = periodStudyRepository.existsById(periodStudyId1);
        if(bool){
            periodStudyRepository.deleteById(periodStudyId1);
        }else{
            throw new IllegalStateException(" Informations are wrong. Maybe one id are wrong.");
        }
    }

    public boolean checkIfExistsById(PeriodStudyId periodStudyId){
         return periodStudyRepository.existsById(periodStudyId);
    }
}
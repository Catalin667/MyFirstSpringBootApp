package myFirstApp.services;

import myFirstApp.entities.PeriodStudy;
import myFirstApp.entities.PeriodStudyId;
import myFirstApp.entities.Student;
import myFirstApp.entities.Subject;
import myFirstApp.repositories.PeriodStudyRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class PeriodStudyServices {
    private final PeriodStudyRepository periodStudyRepository;

    private final SubjectServices subjectServices;

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
            Subject subject = subjectServices.getSubjectById(periodStudy.getPeriodStudyId().getSubjectId());
            System.out.println("PERIODSTUDY: "+periodStudy);
            Set<PeriodStudy> periodsStudyForThisSubject = subject.getPeriodStudy();
            periodsStudyForThisSubject.add(periodStudy);
            subject.setPeriodStudy(periodsStudyForThisSubject);
            periodStudyRepository.save(periodStudy);
        }
    }
    @Transactional
    public void updateANewPeriodStudy(PeriodStudyId periodStudyId, String periodStudyName, String taxPerYear, String yearStudyingNumber) {
        PeriodStudy periodStudy = periodStudyRepository.findById(periodStudyId)
                .orElseThrow(()->new IllegalStateException("Student with id "+ periodStudyId + " does not exists."));

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
}
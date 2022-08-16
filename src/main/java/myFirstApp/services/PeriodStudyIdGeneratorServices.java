package myFirstApp.services;

import myFirstApp.entities.PeriodStudyIdGenerator;
import myFirstApp.repositories.PeriodStudyIdGeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodStudyIdGeneratorServices {

    private final PeriodStudyIdGeneratorRepository periodStudyIdGeneratorRepository;

    @Autowired
    public PeriodStudyIdGeneratorServices(PeriodStudyIdGeneratorRepository periodStudyIdGeneratorRepository) {
        this.periodStudyIdGeneratorRepository = periodStudyIdGeneratorRepository;
    }

    public List<PeriodStudyIdGenerator> getAllgeneratedValues(){
        return periodStudyIdGeneratorRepository.findAll();
    }

    public void generateANewValue() {
        PeriodStudyIdGenerator periodStudyIdGenerator = new PeriodStudyIdGenerator();
        periodStudyIdGeneratorRepository.save(periodStudyIdGenerator);
    }
}

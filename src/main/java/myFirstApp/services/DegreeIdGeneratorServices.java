package myFirstApp.services;

import myFirstApp.entities.DegreeIdGenerator;
import myFirstApp.repositories.DegreeIdGeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DegreeIdGeneratorServices {

    private final DegreeIdGeneratorRepository degreeIdGeneratorRepository;
    @Autowired
    public DegreeIdGeneratorServices(DegreeIdGeneratorRepository degreeIdGeneratorRepository) {
        this.degreeIdGeneratorRepository = degreeIdGeneratorRepository;
    }

    public void generateANewValue(DegreeIdGenerator degreeIdGenerator) {
        degreeIdGeneratorRepository.save(degreeIdGenerator);
    }
}

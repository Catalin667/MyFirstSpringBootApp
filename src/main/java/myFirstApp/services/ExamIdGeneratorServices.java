package myFirstApp.services;

import myFirstApp.entities.ExamIdGenerator;
import myFirstApp.repositories.ExamIdGeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamIdGeneratorServices {
    private final ExamIdGeneratorRepository examIdGeneratorRepository;

    @Autowired
    public ExamIdGeneratorServices(ExamIdGeneratorRepository examIdGeneratorRepository) {
        this.examIdGeneratorRepository = examIdGeneratorRepository;
    }

    public void generateANewValue(ExamIdGenerator examIdGenerator) {
        examIdGeneratorRepository.save(examIdGenerator);
    }
}

package myFirstApp.services;

import myFirstApp.entities.*;
import myFirstApp.repositories.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DegreeServices {

    private final DegreeRepository degreeRepository;


    @Autowired
    public DegreeServices(DegreeRepository degreeRepository) {
        this.degreeRepository = degreeRepository;
    }

    public List<Degree> getAllDegrees() {
        return degreeRepository.findAll();
    }

    public void registerANewDegree(Degree degree) {
        degreeRepository.save(degree);
        }

    public void updateADegree(DegreeId degreeId1,String degreeName) {
        Degree degree = getADegreeById(degreeId1);
        if(degreeName!=null && degreeName.length()>0){
            degree.setDegreeName(degreeName);
        }
    }

    @Transactional
    public Degree getADegreeById(DegreeId degreeId){
        return degreeRepository.findById(degreeId)
                .orElseThrow(()->new IllegalStateException("Degree with id "+ degreeId + " does not exists.")) ;
    }

    public void deleteADegree(DegreeId degreeId1) {
        boolean bool = degreeRepository.existsById(degreeId1);
        if(bool){
            degreeRepository.deleteById(degreeId1);
        }else{
            throw new IllegalStateException(" Informations are wrong. Maybe one id are wrong.");
        }
    }
}
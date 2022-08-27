package myFirstApp.services;

import myFirstApp.entities.ContestStudent;
import myFirstApp.entities.ContestStudentId;
import myFirstApp.repositories.ContestStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ContestStudentServices {
    private final ContestStudentRepository contestStudentRepository;

    @Autowired
    public ContestStudentServices(ContestStudentRepository contestStudentRepository) {
        this.contestStudentRepository = contestStudentRepository;
    }

    public List<ContestStudent> getAllContestStudent() {
        return contestStudentRepository.findAll();
    }


    public void registerAContestStudent(ContestStudent contestStudent) {
        List<ContestStudent> contestStudentList = getAllContestStudent();

        for(ContestStudent cs:contestStudentList){
            if(cs.equals(contestStudent)){
                throw new IllegalStateException("This registration already exists.");
            }
        }

        if("I".equals(contestStudent.getPrize())
                || "II".equals(contestStudent.getPrize())
                || "III".equals(contestStudent.getPrize())
                || "Mentiune I".equals(contestStudent.getPrize())
                || "Mentiune II".equals(contestStudent.getPrize())
                || "Mentiune III".equals(contestStudent.getPrize())) {
            contestStudentRepository.save(contestStudent);
        }else{
            throw new IllegalStateException("The prize entered is not valid.");
        }
    }
    @Transactional
    public void updateAContestStudent(ContestStudentId contestStudentId, String prize) {
         ContestStudent contestStudent = getContestStudentById(contestStudentId);

         if(prize!=null&&prize.length()>0 && !prize.equals(contestStudent.getPrize())&&
                 ("I".equals(contestStudent.getPrize())
                         || "II".equals(contestStudent.getPrize())
                         || "III".equals(contestStudent.getPrize())
                         || "Mentiune I".equals(contestStudent.getPrize())
                         || "Mentiune II".equals(contestStudent.getPrize())
                         || "Mentiune III".equals(contestStudent.getPrize()))){
             contestStudent.setPrize(prize);
         }
    }
    public void deleteAContestStudent(ContestStudentId contestStudentId) {
        if(checkIfContestStudentExists(contestStudentId)){
            contestStudentRepository.deleteById(contestStudentId);
        }else{
            throw new IllegalStateException("This registration does not exist.");
        }
    }
    public ContestStudent getContestStudentById(ContestStudentId contestStudentId){
        return contestStudentRepository.findById(contestStudentId)
                .orElseThrow(()->new IllegalStateException("This id is wrong."));
    }

    public boolean checkIfContestStudentExists(ContestStudentId contestStudentId){
        return contestStudentRepository.existsById(contestStudentId);
    }


}

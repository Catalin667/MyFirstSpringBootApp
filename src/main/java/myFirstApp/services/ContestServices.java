package myFirstApp.services;

import myFirstApp.entities.Contest;
import myFirstApp.entities.Directory;
import myFirstApp.repositories.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class ContestServices {
    private final ContestRepository contestRepository;
    @Autowired
    public ContestServices(ContestRepository contestRepository) {
        this.contestRepository = contestRepository;
    }

    public List<Contest> getAllContests() {
        return contestRepository.findAll();
    }

    public void registerAContest(Contest contest) {
        List<Contest> contests = getAllContests();

        for(Contest c:contests){
            if(contest.equals(c)){
                throw new IllegalStateException("This contest already exists.");
            }
        }
        contestRepository.save(contest);
    }
    @Transactional
    public void updateAContest(long contestId,String contestDateString, String city, Directory directory) {
       Contest contest = getContestById(contestId);

       if(contestDateString!=null && contestDateString.length()>0){
           LocalDate contestDate = LocalDate.parse(contestDateString);
           if(!contestDate.equals(contest.getContestDate())){
               contest.setContestDate(contestDate);
           }
       }

       if(city!=null && city.length()>0 && !city.equals(contest.getCity())){
           contest.setCity(city);
       }

       if(directory!=null){
           long directoryId = directory.getEmployeeId();
           contest.setDirectoryId(directoryId);
       }
    }


    public boolean checkIfExistsAContestById(long contestId){
        return contestRepository.existsById(contestId);
    }

    public Contest getContestById(long contestId){
        return  contestRepository.findById(contestId)
                .orElseThrow(()->new IllegalStateException("Contest with id "+ contestId + " does not exist.")) ;
    }

    public void deleteAContest(long contestId) {
        if(checkIfExistsAContestById(contestId)){
            contestRepository.deleteById(contestId);
        }else{
            throw new IllegalStateException("This contest does not exist.");
        }
    }
}

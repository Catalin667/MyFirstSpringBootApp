package myFirstApp.controllers;

import myFirstApp.entities.Contest;
import myFirstApp.entities.Directory;
import myFirstApp.services.ContestServices;
import myFirstApp.services.DirectoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="api/contest")
public class ContestController {
    private final ContestServices contestServices;

    private final DirectoryServices directoryServices;
    @Autowired
    public ContestController(ContestServices contestServices, DirectoryServices directoryServices) {
        this.contestServices = contestServices;
        this.directoryServices = directoryServices;
    }

    @GetMapping
    public List<Contest> getAllContests(){
        return contestServices.getAllContests();
    }

    @PostMapping
    public void registerAContest(@RequestBody Contest contest){
        long directoryId = contest.getDirectoryId();
        if(directoryServices.checkIfADirectoryExistsById(directoryId)){
            contestServices.registerAContest(contest);
        }else{
            throw new IllegalStateException("Directory with id "+ directoryId + " does not exist.");
        }
    }

    @PutMapping(path="{contestId}")
    public void updateAContest(@PathVariable ("contestId") long contestId,
                               @RequestParam (required = false)String contestDate,
                               @RequestParam (required = false)String city,
                               @RequestParam(required = false) String directoryId){
        if(directoryId!=null){
            long directoryIdLong = Long.parseLong(directoryId);
            if(directoryServices.checkIfADirectoryExistsById(directoryIdLong)) {
                Directory directory = directoryServices.getADirectoryById(directoryIdLong);
                contestServices.updateAContest(contestId,contestDate, city,directory);
            }else {
                throw new IllegalStateException("This directoryId is not exists.");
            }
        }
        contestServices.updateAContest(contestId,contestDate, city,null);
    }

    @DeleteMapping(path="{contestId}")
    public void deleteAContest(@PathVariable ("contestId") long contestId){
        contestServices.deleteAContest(contestId);
    }
}

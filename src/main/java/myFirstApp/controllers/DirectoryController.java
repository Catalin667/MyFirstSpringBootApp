package myFirstApp.controllers;

import myFirstApp.entities.Directory;
import myFirstApp.services.DirectoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/directory")
public class DirectoryController {
    private final DirectoryServices directoryServices;
    @Autowired
    public DirectoryController(DirectoryServices directoryServices) {
        this.directoryServices = directoryServices;
    }

    @GetMapping
    public List<Directory> getAllDirectors(){
        return directoryServices.getAllDirectors();
    }

    @PostMapping
    public void registerANewDirectory(@RequestBody Directory directory){
        directoryServices.registerANewDirectory(directory);
    }

    @PutMapping(path="{directoryId}")
    public void updateADirectory(@PathVariable ("directoryId") long directoryId,
                                 @RequestParam(required = false) String lastName,
                                 @RequestParam(required = false) String firstName,
                                 @RequestParam(required = false) String city,
                                 @RequestParam(required = false)String phoneNumber,
                                 @RequestParam(required = false) String email,
                                 @RequestParam(required = false) String hireDate,
                                 @RequestParam(required = false) String salary,
                                 @RequestParam(required = false) String startYear,
                                 @RequestParam(required = false) String stopYear)
    {
        directoryServices.updateADirectory(directoryId,lastName,firstName,city,phoneNumber,email,hireDate,salary,startYear,stopYear);
    }

    @DeleteMapping(path="{directoryId}")
    public void deleteADirectory(@PathVariable ("directoryId") long directoryId){
        directoryServices.deleteADirectory(directoryId);
    }
}

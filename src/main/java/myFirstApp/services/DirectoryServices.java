package myFirstApp.services;

import myFirstApp.entities.Directory;
import myFirstApp.entities.Teacher;
import myFirstApp.repositories.DirectoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class DirectoryServices {
    private final DirectoryRepository directoryRepository;

    public DirectoryServices(DirectoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;
    }

    public List<Directory> getAllDirectors() {
        return directoryRepository.findAll();
    }

    public void registerANewDirectory(Directory directory) {
        List<Directory> directors = getAllDirectors();

        for(Directory d:directors){
            if(d.equals(directory)){
                throw new IllegalStateException("This directory already exists.");
            }
        }
        directoryRepository.save(directory);
    }

    @Transactional
    public void updateADirectory(long directoryId, String lastName, String firstName, String city, String phoneNumber, String email, String hireDate, String salary, String startYear, String stopYear) {
        Directory directory = directoryRepository.findById(directoryId)
                .orElseThrow(()->new IllegalStateException("Directory with id "+ directoryId + " does not exists."));

        if(lastName!=null && lastName.length()>0&& !lastName.equals(directory.getLastName())){
            directory.setLastName(lastName);
        }

        if(firstName!=null && firstName.length()>0&& !firstName.equals(directory.getFirstName())){
            directory.setFirstName(firstName);
        }

        if(city!=null && city.length()>0&& !city.equals(directory.getCity())){
            directory.setCity(city);
        }

        if(phoneNumber!=null && phoneNumber.length()>0&& !phoneNumber.equals(directory.getPhoneNumber())){
            directory.setPhoneNumber(phoneNumber);
        }

        if(email!=null && email.length()>0&& !email.equals(directory.getEmail())){
            directory.setEmail(email);
        }

        if(hireDate!=null) {
            SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-YYYY");
            try {
                Date result = sdt.parse(hireDate);
                LocalDate localDate = Instant.ofEpochMilli(result.getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                if(!result.equals(directory.getHireDate())){
                    directory.setHireDate(localDate);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        if( salary!=null) {
            double salaryDouble = Double.parseDouble(salary);
            if (salaryDouble > 500 && salaryDouble != directory.getSalary()) {
                directory.setSalary(salaryDouble);
            }
        }

        if (startYear != null && startYear.length() == 4) {
            int startYearInt = Integer.parseInt(startYear);
            if (startYearInt > 1955 && startYearInt <= LocalDate.now().getYear()) {
                directory.setStartYear(startYearInt);
            } else {
                throw new IllegalStateException("Invalid year.");
            }
        } else {
            throw new IllegalStateException("Invalid year.");
        }

        if(stopYear!=null && stopYear.length()==4){
            int stopYearInt = Integer.parseInt(stopYear);
            if(stopYearInt>directory.getHireDate().getYear() && stopYearInt<=LocalDate.now().getYear()){
                directory.setStopYear(stopYearInt);
            }else{
                throw new IllegalStateException("Invalid year.");
            }
        }else{
            throw new IllegalStateException("Invalid year.");
        }
    
    }


    public void deleteADirectory(long directoryId) {
        boolean directoryExists = directoryRepository.existsById(directoryId);
        if(directoryExists){
            directoryRepository.deleteById(directoryId);
        }else{
            throw new IllegalStateException("Directory with id "+ directoryId + " does not exists!");
        }
    }
}

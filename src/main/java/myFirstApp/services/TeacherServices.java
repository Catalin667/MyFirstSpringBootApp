package myFirstApp.services;

import myFirstApp.entities.Teacher;
import myFirstApp.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServices {
    private final TeacherRepository teacherRepository;

    public TeacherServices(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
    public void addNewTeacher(Teacher teacher) {
        List<Teacher> teachers = getAllTeachers();
        for(Teacher t: teachers){
            if(t.equals(teacher)){
                throw new IllegalStateException("This teacher already exists.");
            }
        }
        teacherRepository.save(teacher);
    }

    @Transactional
    public void updateATeacher(long teacherId, String lastName, String firstName, String city, String phoneNumber, String email, String hireDate, String salary, String specializations) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()->new IllegalStateException("Teacher with id "+ teacherId + " does not exists."));

        if(lastName!=null && lastName.length()>0&& !lastName.equals(teacher.getLastName())){
            teacher.setLastName(lastName);
        }

        if(firstName!=null && firstName.length()>0&& !firstName.equals(teacher.getFirstName())){
            teacher.setFirstName(firstName);
        }

        if(city!=null && city.length()>0&& !city.equals(teacher.getCity())){
            teacher.setCity(city);
        }

        if(phoneNumber!=null && phoneNumber.length()>0&& !phoneNumber.equals(teacher.getPhoneNumber())){
            teacher.setPhoneNumber(phoneNumber);
        }

        if(email!=null && email.length()>0&& !email.equals(teacher.getEmail())){
            teacher.setEmail(email);
        }

        if(hireDate!=null) {
            SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-YYYY");
            try {
                Date result = sdt.parse(hireDate);
                LocalDate localDate = Instant.ofEpochMilli(result.getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                if(!result.equals(teacher.getHireDate())){
                    teacher.setHireDate(localDate);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        if( salary!=null) {
            double salaryDouble = Double.parseDouble(salary);
            if (salaryDouble > 500 && salaryDouble != teacher.getSalary()) {
                teacher.setSalary(salaryDouble);
            }
        }

        if(specializations!=null && !(teacher.getSpecializations()).equals(specializations)){
            teacher.setSpecializations(specializations);
        }
    }

    public void deleteATeacher(long teacherId) {
        boolean teacherExists = teacherRepository.existsById(teacherId);
        if(teacherExists){
            teacherRepository.deleteById(teacherId);
        }else{
            throw new IllegalStateException("Teacher with id "+ teacherId + " does not exists!");
        }
    }

    public Teacher getTeacherById(long id){
        return teacherRepository.findById(id)
                .orElseThrow(()->new IllegalStateException("Teacher with id "+ id + " does not exists."));
    }

    public boolean checkIfExistsById(long id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isEmpty()){
             return false;
        }
        return true;
    }
}

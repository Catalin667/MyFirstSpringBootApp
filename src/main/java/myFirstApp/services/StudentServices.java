package myFirstApp.services;

import myFirstApp.entities.Directory;
import myFirstApp.entities.Student;
import myFirstApp.entities.Subject;
import myFirstApp.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServices {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentServices(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public void registerANewStudent(Student student) {
        List<Student> students = getAllStudents();

        for(Student s:students){
            if(s.equals(student)){
                throw new IllegalStateException("This student already exists.");
            }
        }
        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(long studentId, String lastName, String firstName, String city, String email, String ocupation, String phoneNumber) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->new IllegalStateException("Student with id "+ studentId + " does not exist."));

        if(lastName!=null && lastName.length()>0&& !lastName.equals(student.getLastName())){
            student.setLastName(lastName);
        }

        if(firstName!=null && firstName.length()>0&& !firstName.equals(student.getFirstName())){
            student.setFirstName(firstName);
        }

        if(city!=null && city.length()>0&& !city.equals(student.getCity())){
            student.setCity(city);
        }

        if(email!=null && email.length()>0&& !email.equals(student.getEmail())){
            student.setEmail(email);
        }

        if(ocupation!=null && ocupation.length()>0&& !ocupation.equals(student.getOccupation())){
            student.setOccupation(ocupation);
        }

        if(phoneNumber!=null && phoneNumber.length()>0&& !phoneNumber.equals(student.getPhone())){
            student.setPhone(phoneNumber);
        }
    }

    public void deleteStudent(long studentId) {
        boolean bool = studentRepository.existsById(studentId);
        if (bool){
            studentRepository.deleteById(studentId);
        }else{
            throw new IllegalStateException("Student with id "+ studentId + " does not exist!");
        }
        studentRepository.findById(studentId);
    }

    public Student getStudentById(long studentId){
        return studentRepository.findById(studentId)
                .orElseThrow(()->new IllegalStateException("Student with id "+ studentId + " does not exist."));
    }

    public boolean checkIfExistsById(long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isEmpty()){
            return false;
        }
        return true;
    }
}
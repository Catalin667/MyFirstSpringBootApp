package myFirstApp.services;

import myFirstApp.entities.StudentExam;
import myFirstApp.entities.StudentExamId;
import myFirstApp.repositories.StudentExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentExamServices {
    private final StudentExamRepository studentExamRepository;

    @Autowired
    public StudentExamServices(StudentExamRepository studentExamRepository) {
        this.studentExamRepository = studentExamRepository;
    }

    public List<StudentExam> getAllExamsTaken() {
        return studentExamRepository.findAll();
    }

    public void registerAExamTaken(StudentExam studentExam) {
        boolean bool = checkIfExistsById(studentExam.getStudentExamId());
        if(!bool)
        {studentExamRepository.save(studentExam);
    }else{
            throw new IllegalStateException("This already exists.");
        }
    }

    public void updateAExamTaken(StudentExamId studentExamId, String grade, String examDate) {
        StudentExam studentExam = getAExamTakenById(studentExamId);
    }

    public boolean checkIfExistsById(StudentExamId studentExamId){
        return studentExamRepository.existsById(studentExamId);
    }
    public StudentExam getAExamTakenById(StudentExamId studentExamId){
        return studentExamRepository.findById(studentExamId)
                .orElseThrow(()->new IllegalStateException("Id "+ studentExamId+"is wrong."));
    }

    public void deleteAExamTaken(StudentExamId studentExamId) {
        boolean bool = checkIfExistsById(studentExamId);
        if(bool){
            studentExamRepository.deleteById(studentExamId);
        }else{
            throw new IllegalStateException("This registration does not exist");
        }
    }
}
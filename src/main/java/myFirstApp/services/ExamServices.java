package myFirstApp.services;

import myFirstApp.entities.Exam;
import myFirstApp.entities.ExamId;
import myFirstApp.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServices {

    private final ExamRepository examRepository;

    @Autowired
    public ExamServices(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public void registerANewExam(Exam exam) {
        examRepository.save(exam);
    }


    public void updateAnExam(ExamId examFullId, String examName, String year) {
        Exam exam = getExamById(examFullId);
        if(examName!=null && examName.length()>0 && !examName.equals(exam.getExamName())){
            exam.setExamName(examName);
        }

        if(year!=null && year.length()>0){
            int yearInt = Integer.parseInt(year);
            if(yearInt>0 && yearInt<11 && yearInt!=exam.getYear()){
                exam.setYear(yearInt);
            }
        }
    }

    public Exam getExamById(ExamId examId){
        return examRepository.findById(examId)
                .orElseThrow(()->new IllegalStateException("Exam with id "+ examId + " does not exists.")) ;
    }

    public void deleteAnExam(ExamId examFullId) {
        boolean bool = examRepository.existsById(examFullId);
        if(bool){
            examRepository.deleteById(examFullId);
        }else{
            throw new IllegalStateException("Informations are wrong. Maybe one id are wrong.");
        }
    }
}

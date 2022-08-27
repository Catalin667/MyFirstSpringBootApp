package myFirstApp.services;

import myFirstApp.entities.PeriodStudy;
import myFirstApp.entities.Subject;
import myFirstApp.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SubjectServices {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServices(SubjectRepository subjectRepository ) {
        this.subjectRepository = subjectRepository;
    }

      public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
      }

    public void addNewSubject(Subject subject) {
        List<Subject> subjects = getAllSubjects();
        for(Subject s : subjects){
            if(s.equals(subject)){
                throw new IllegalStateException("This subject already exists. ");
            }
        }
        subjectRepository.save(subject);
    }
    @Transactional
    public void updateASubject(long subjectId, String subjectName, String hoursCourseNumber, String examsNumber) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(()->new IllegalStateException("Subject with id "+ subjectId + " does not exist."));

        if(subjectName!=null && subjectName.length()>0 && !subjectName.equals(subject.getSubjectName())){
            subject.setSubjectName(subjectName);
        }

        if(hoursCourseNumber!=null && hoursCourseNumber.length()>0){
            int hoursCourseNumberInt = Integer.parseInt(hoursCourseNumber);
            if(hoursCourseNumberInt>0 && hoursCourseNumberInt!=subject.getHoursCourseNumber()){
                subject.setHoursCourseNumber(hoursCourseNumberInt);
            }
        }

        if(examsNumber!=null && examsNumber.length()>0){
            int examsNumberInt = Integer.parseInt(examsNumber);
            if(examsNumberInt>0&& examsNumberInt!=subject.getExamNumber()){
                subject.setExamNumber(examsNumberInt);
            }
        }
    }

    @Transactional
    public void addNewPeriodStudy(long subjectId, PeriodStudy periodStudy){
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(()->new IllegalStateException("Subject with id "+ subjectId + " does not exist."));
        if(periodStudy!=null){
            Set<PeriodStudy> periodStudies = subject.getPeriodsStudy();
            periodStudies.add(periodStudy);
            subject.setPeriodStudy(periodStudies);
        }
    }

    public void deleteSubject(long subjectId) {
        boolean bool = subjectRepository.existsById(subjectId);
        if (bool){
            subjectRepository.deleteById(subjectId);
        }else{
            throw new IllegalStateException("Subject with id "+ subjectId + " does not exist!");
        }
        subjectRepository.findById(subjectId);
    }
    public Subject getSubjectById(long subjectId){
        return subjectRepository.findById(subjectId)
                .orElseThrow(()->new IllegalStateException("Subject with id "+ subjectId + " does not exist."));
    }

    public boolean checkIfExistsById(long subjectId) {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(subject.isEmpty()){
            return false;
        }
        return true;
    }
}

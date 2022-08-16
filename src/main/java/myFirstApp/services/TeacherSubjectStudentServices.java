package myFirstApp.services;

import myFirstApp.entities.*;
import myFirstApp.repositories.TeacherSubjectStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherSubjectStudentServices {
    private final TeacherSubjectStudentRepository teacherSubjectStudentRepository;

    private final TeacherServices teacherServices;
    private final SubjectServices subjectServices;
    private final StudentServices studentServices;
    @Autowired
    public TeacherSubjectStudentServices(TeacherSubjectStudentRepository teacherSubjectStudentRepository, TeacherServices teacherServices, SubjectServices subjectServices, StudentServices studentServices) {
        this.teacherSubjectStudentRepository = teacherSubjectStudentRepository;
        this.teacherServices = teacherServices;
        this.subjectServices = subjectServices;
        this.studentServices = studentServices;
    }

    public List<TeacherSubjectStudent> getAll() {
        return teacherSubjectStudentRepository.findAll();
    }

    public void createTeacherSubjectStudent(TeacherSubjectStudent teacherSubjectStudent) {
        List<TeacherSubjectStudent> data = getAll();
        for(TeacherSubjectStudent tss : data){
            if(tss.equals(teacherSubjectStudent)){
                throw new IllegalStateException("Something went wrong");
            }
        }

        if(teacherServices.checkIfExistsById(teacherSubjectStudent.getTeacherSubjectStudentId().getEmployeeId())
        && subjectServices.checkIfExistsById(teacherSubjectStudent.getTeacherSubjectStudentId().getSubjectId())
        && studentServices.checkIfExistsById(teacherSubjectStudent.getTeacherSubjectStudentId().getStudentId())){
        Teacher teacher = teacherServices.getTeacherById(teacherSubjectStudent.getTeacherSubjectStudentId().getEmployeeId());
        Subject subject =subjectServices.getSubjectById(teacherSubjectStudent.getTeacherSubjectStudentId().getSubjectId());
        Student student =studentServices.getStudentById(teacherSubjectStudent.getTeacherSubjectStudentId().getStudentId());

        teacherSubjectStudent.setTeacher(teacher);
        teacherSubjectStudent.setSubject(subject);
        teacherSubjectStudent.setStudent(student);
        teacherSubjectStudentRepository.save(teacherSubjectStudent);
        }else{
            throw new IllegalStateException("Something went wrong");
        }
    }
    @Transactional
    public void update(TeacherSubjectStudentId tss, String year) {
        TeacherSubjectStudent objById = teacherSubjectStudentRepository.findById(tss)
                .orElseThrow(()->new IllegalStateException("Teacher,subject or student with this id does not exists, or informations are wrong."));

        if(year!=null && year.length()>0){
            int yearInt = Integer.parseInt(year);
            if(yearInt>0){
                objById.setYear(yearInt);
            }
        }
    }

    public void delete(TeacherSubjectStudentId tss) {
        boolean bool = teacherSubjectStudentRepository.existsById(tss);
        if(bool){
            teacherSubjectStudentRepository.deleteById(tss);
        }else{
            throw new IllegalStateException("Teacher,subject or student with this id does not exists, or informations are wrong.");
        }
    }
}

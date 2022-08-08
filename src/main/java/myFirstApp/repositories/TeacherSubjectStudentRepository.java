package myFirstApp.repositories;

import myFirstApp.entities.TeacherSubjectStudent;
import myFirstApp.entities.TeacherSubjectStudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherSubjectStudentRepository extends JpaRepository<TeacherSubjectStudent,TeacherSubjectStudentId> {
}

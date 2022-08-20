package myFirstApp.repositories;

import myFirstApp.entities.StudentExam;
import myFirstApp.entities.StudentExamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentExamRepository extends JpaRepository<StudentExam, StudentExamId> {
}

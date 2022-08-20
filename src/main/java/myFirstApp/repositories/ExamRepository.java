package myFirstApp.repositories;

import myFirstApp.entities.Exam;
import myFirstApp.entities.ExamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, ExamId> {
}

package myFirstApp.repositories;

import myFirstApp.entities.ExamIdGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamIdGeneratorRepository extends JpaRepository<ExamIdGenerator,Long> {
}

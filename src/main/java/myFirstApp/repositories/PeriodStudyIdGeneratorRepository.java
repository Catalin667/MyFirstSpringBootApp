package myFirstApp.repositories;

import myFirstApp.entities.PeriodStudyIdGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodStudyIdGeneratorRepository extends JpaRepository<PeriodStudyIdGenerator,Long> {
}

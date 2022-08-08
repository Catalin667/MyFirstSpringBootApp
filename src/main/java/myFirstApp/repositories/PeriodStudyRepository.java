package myFirstApp.repositories;

import myFirstApp.entities.PeriodStudy;
import myFirstApp.entities.PeriodStudyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodStudyRepository extends JpaRepository<PeriodStudy, PeriodStudyId> {
}

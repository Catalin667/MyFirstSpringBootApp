package myFirstApp.repositories;

import myFirstApp.entities.PeriodStudy;
import myFirstApp.entities.PeriodStudyId;
import org.hibernate.tool.schema.extract.spi.PrimaryKeyInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodStudyRepository extends JpaRepository<PeriodStudy, PeriodStudyId> {
}

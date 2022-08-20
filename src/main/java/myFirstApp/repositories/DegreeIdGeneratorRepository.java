package myFirstApp.repositories;

import myFirstApp.entities.DegreeIdGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeIdGeneratorRepository extends JpaRepository<DegreeIdGenerator,Long> {
}

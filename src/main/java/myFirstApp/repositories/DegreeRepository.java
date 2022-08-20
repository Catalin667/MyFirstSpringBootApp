package myFirstApp.repositories;

import myFirstApp.entities.Degree;
import myFirstApp.entities.DegreeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, DegreeId> {
}

package myFirstApp.repositories;

import myFirstApp.entities.ContestStudent;
import myFirstApp.entities.ContestStudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestStudentRepository extends JpaRepository<ContestStudent, ContestStudentId> {

}

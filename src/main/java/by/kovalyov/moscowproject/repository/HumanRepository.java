package by.kovalyov.moscowproject.repository;

import by.kovalyov.moscowproject.entity.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends JpaRepository<Human, Long> {
     Human findFirstByName(String name);
}

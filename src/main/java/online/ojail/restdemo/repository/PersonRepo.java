package online.ojail.restdemo.repository;

import online.ojail.restdemo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Long> {

}

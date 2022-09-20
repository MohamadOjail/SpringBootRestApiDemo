package online.ojail.restdemo.repository;

import online.ojail.restdemo.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepo extends JpaRepository<Cat, Long> {
}

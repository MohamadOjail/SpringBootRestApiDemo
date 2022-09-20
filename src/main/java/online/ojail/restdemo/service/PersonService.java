package online.ojail.restdemo.service;

import online.ojail.restdemo.model.Person;

import java.util.List;

public interface PersonService {

    Person save(Person person);

    Person findById(Long id);
    List<Person> findAll();

    Person update(Person person);

    Person addCat(Long personId, Long catId);
    Person removeCat(Long personId, Long catId);
    Person removeAllCats(Long personId);

    void deleteById(Long id);

    boolean exists(Long id);

    Long count();
}

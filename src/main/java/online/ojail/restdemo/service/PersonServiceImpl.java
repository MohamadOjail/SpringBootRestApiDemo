package online.ojail.restdemo.service;

import online.ojail.restdemo.exception.ResourceNotFoundException;
import online.ojail.restdemo.model.Cat;
import online.ojail.restdemo.model.Person;
import online.ojail.restdemo.repository.CatRepo;
import online.ojail.restdemo.repository.PersonRepo;
import online.ojail.restdemo.utils.MethodValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mo. Ojail
 * created: 2022-09-20
 */

@Service @Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private CatRepo catRepo;

    @Autowired private PersonRepo personRepo;

    @Override
    public Person save(Person person) {return personRepo.save(person);}

    @Override
    public Person findById(Long id) {
        MethodValidations.validateNotNullID(id, Person.class);
        return personRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Person.class)
        );
    }

    @Override
    public List<Person> findAll() {
        return personRepo.findAll();
    }

    @Override
    public Person update(Person person) {
        MethodValidations.validateNotNullID(person.getId(), Person.class);
        if (!exists(person.getId())) throw  new ResourceNotFoundException(Person.class);
        return personRepo.save(person);
    }

    @Override
    public Person addCat(Long personId, Long catId) {
        Person person = findById(personId);
        MethodValidations.validateNotNullID(catId, Cat.class);

        Cat cat = catRepo.findById(catId).orElseThrow(
                () -> new RuntimeException(String.format("Cat with id: [%s] Not Found.", personId))
        );

        if (cat.getOwner() != null) throw  new RuntimeException("Cat Already has an Owner.");
        person.addCat(cat);
        cat.setOwner(person);
        catRepo.save(cat);
        return personRepo.save(person);
    }

    @Override
    public Person removeCat(Long personId, Long catId) {
        Person person = findById(personId);
        MethodValidations.validateNotNullID(catId, Cat.class);

        Cat cat = catRepo.findById(catId).orElseThrow(
                () -> new ResourceNotFoundException(Cat.class)
        );

        if (cat.getOwner() != person) throw  new RuntimeException("Cat does not belong to this person");
        cat.removeOwner();
        person.removeCat(catId);
        catRepo.save(cat);
        return personRepo.save(person);
    }

    @Override
    public Person removeAllCats(Long personId) {
        Person person = findById(personId);
        person.getCats().forEach(cat -> {
            cat.removeOwner();
            catRepo.save(cat);
        });
        person.removeAllCats();
        return personRepo.save(person);
    }

    @Override
    public void deleteById(Long id) {
        Person person = removeAllCats(id);
        personRepo.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {return personRepo.existsById(id);}

    @Override
    public Long count() {
        return personRepo.count();
    }
}

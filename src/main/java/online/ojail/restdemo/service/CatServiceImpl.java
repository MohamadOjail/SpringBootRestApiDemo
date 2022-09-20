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
public class CatServiceImpl implements CatService {

    @Autowired private CatRepo catRepo;

    @Autowired private PersonRepo personRepo;

    @Override
    public Cat save(Cat cat) {return catRepo.save(cat);}

    @Override
    public Cat findById(Long id) {
        MethodValidations.validateNotNullID(id, Cat.class);
        return catRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Cat.class)
        );
    }

    @Override
    public List<Cat> findAll() {
        return catRepo.findAll();
    }

    @Override
    public Cat update(Cat cat) {
        MethodValidations.validateNotNullID(cat.getId(), Cat.class);
        if (!exists(cat.getId())) throw  new ResourceNotFoundException(Cat.class);
        return catRepo.save(cat);
    }

    @Override
    public Cat addOwner(Long catId, Long personId) {

        Cat cat = findById(catId);
        MethodValidations.validateNotNullID(personId, Person.class);

        Person person = personRepo.findById(personId).orElseThrow(
                () -> new ResourceNotFoundException(Person.class)
        );
        if (cat.getOwner() != null) throw  new RuntimeException("Cat Already has an Owner.");
        person.addCat(cat);
        cat.setOwner(person);
        personRepo.save(person);
        return catRepo.save(cat);
    }

    @Override
    public Cat removeOwner(Long catId) {
        Cat cat = findById(catId);
        if (cat.getOwner() == null) throw  new RuntimeException("Cat has No Owner.");
        Person person = personRepo.getById(cat.getOwner().getId());
        cat.removeOwner();
        person.removeCat(catId);
        personRepo.save(person);
        return catRepo.save(cat);
    }

    @Override
    public void deleteById(Long id) {
        Cat cat = findById(id);
        if (cat.getOwner() == null){
            catRepo.deleteById(id);
            return;
        }
        Person person = personRepo.getById(cat.getOwner().getId());
        cat.removeOwner();
        person.removeCat(id);
        personRepo.save(person);
        catRepo.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return catRepo.existsById(id);
    }

    @Override
    public Long count() {
        return catRepo.count();
    }
}

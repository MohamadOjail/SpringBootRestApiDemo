package online.ojail.restdemo.service;

import online.ojail.restdemo.model.Cat;

import java.util.List;

public interface CatService {

    Cat save(Cat cat);

    Cat findById(Long id);
    List<Cat> findAll();

    Cat update(Cat cat);
    Cat addOwner(Long catId, Long personId);
    Cat removeOwner(Long catId);

    void deleteById(Long id);

    boolean exists(Long id);

    Long count();
}

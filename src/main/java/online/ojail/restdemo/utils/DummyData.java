package online.ojail.restdemo.utils;

import com.github.javafaker.Faker;
import online.ojail.restdemo.model.Cat;
import online.ojail.restdemo.model.Person;
import online.ojail.restdemo.service.CatServiceImpl;
import online.ojail.restdemo.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

/**
 * @author Mo. Ojail
 * created: 2022-09-20
 */

@Component
public class DummyData implements CommandLineRunner {

    @Autowired private PersonServiceImpl personService;
    @Autowired private CatServiceImpl catService;
    @Autowired private Faker faker;
    @Autowired private Random rnd;

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 15; i++) {
            catService.save(
                    new Cat(faker.cat().name())
            );
        }

        for (int i = 0; i < 5; i++) {
            personService.save(
                    new Person(faker.name().fullName(), faker.internet().emailAddress())
            );
        }

        personService.findAll().forEach(person -> {
            for (int x = 0; x < rnd.nextInt(5); x++) {
                Optional<Cat> first = catService.findAll().stream().filter(cat -> cat.getOwner() == null).findFirst();
                first.ifPresent(cat -> {
                    catService.addOwner(cat.getId(), person.getId());
                });
            }
        });

    }
}

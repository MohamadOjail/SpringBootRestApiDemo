package online.ojail.restdemo.controller;

import online.ojail.restdemo.model.Person;
import online.ojail.restdemo.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mo. Ojail
 * created: 2022-09-20
 */

@RestController @RequestMapping("/api")
public class PersonController {

    @Autowired private PersonServiceImpl personService;

    @PostMapping("/person/new")
    public ResponseEntity<Person> saveNew(@RequestBody Person person){
        return new ResponseEntity<>(personService.save(person), HttpStatus.CREATED);
    }

    @GetMapping("/person")
    public ResponseEntity<Person> findById(@RequestHeader Long id){
        return new ResponseEntity<>(personService.findById(id), HttpStatus.FOUND);
    }

    @GetMapping("/people")
    public ResponseEntity<List<Person>> findAll(){
        return new ResponseEntity<>(personService.findAll(), HttpStatus.FOUND);
    }

    @PutMapping("/person/cats/add")
    public ResponseEntity<Person> addCatToPerson(@RequestHeader Long personId, @RequestHeader Long catId){
        Person person = personService.addCat(personId, catId);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PutMapping("/person/cats/remove/all")
    public ResponseEntity<Person> removeAllCats(@RequestHeader Long id){
        Person person = personService.removeAllCats(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PutMapping("/person/cats/remove/one")
    public ResponseEntity<Person> removeCatById(@RequestHeader Long personId, @RequestHeader Long catId){
        Person person = personService.removeCat(personId, catId);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/people/delete/one")
    public ResponseEntity<Object> deletePerson(@RequestHeader Long id){
        personService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package online.ojail.restdemo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Mo. Ojail
 * created: 2022-09-20
 */


@Entity
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_cats",
            joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cat_id", referencedColumnName = "id")
    )
    private List<Cat> cats = new ArrayList<>();

    public Person() {}

    public Person(String name, String email, List<Cat> cats) {
        this.name = name;
        this.email = email;
        this.cats = cats;
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Person(Long id, String name, String email, List<Cat> cats) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cats = cats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Person: " + name;
    }

    public void removeAllCats(){this.cats = new ArrayList<>();}

    public void removeCat(Long catId){
        if (catId == null) throw new RuntimeException("Supplied Cat id is null.");
        Cat found = this.cats.stream()
                .filter(cat -> cat.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cat not owned by person."));
        this.cats.remove(found);
    }

    public void addCat(Cat cat){
        if (this.cats.contains(cat))
            throw new RuntimeException(String.format("%s Already owns %s", this.name, cat.getName()));
        this.cats.add(cat);
    }
}

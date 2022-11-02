package doerr.alex.fantasyworldapi.BLL;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import doerr.alex.fantasyworldapi.model.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PersonBLL {

    private final File file = new File("person.json");

    public void save(List<Person> people) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(file, people);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Person person) {
        List<Person> people = findAll();
        people.add(person);
        save(people);
    }

    //update person
    public void update(Person person) {
        List<Person> people = findAll();
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId() == person.getId()) {
                people.set(i, person);
                save(people);
                return;
            }
        }
        throw new RuntimeException("Person not found");
    }

    public Person findById(int id) {
        return findAll().stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public void delete(int id) {
        List<Person> people = findAll();
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId() == id) {
                people.remove(i);
                save(people);
                return;
            }
        }
        throw new RuntimeException("Person not found");
    }
    public List<Person> findAll() {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();// use this if using less common types like LocalDateTime
        try {
            List<Person> people = mapper.readValue(file, new TypeReference<List<Person>>() {
            });
            System.out.println(people.toString());
            return people;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

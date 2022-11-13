package doerr.alex.fantasyworldapi.controller;

import doerr.alex.fantasyworldapi.BLL.PersonBLL;
import doerr.alex.fantasyworldapi.model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonBLL pb = new PersonBLL();

    @RequestMapping(path="", method= RequestMethod.POST)
    public void createPerson(@RequestBody Person person) {
        pb.createPerson(person);
    }

    @RequestMapping(path="/{id}", method=RequestMethod.PUT)
    public void updatePerson(@RequestBody Person person) {
        pb.updatePerson(person);
    }

    @RequestMapping(path="/{id}", method=RequestMethod.GET)
    public Person findPersonById(@PathVariable int id) {
        System.out.println("findPersonById");
        return pb.findPerson(id);
    }

    @RequestMapping(path="", method=RequestMethod.GET)
    public List<Person> findAllPeople() {
        System.out.printf("findAllPeople");
//        return pb.findAll();
        return pb.selectPeople();
    }

    @RequestMapping(path="/{id}", method=RequestMethod.DELETE)
    public void deletePersonById(@PathVariable int id) {
        pb.deletePerson(id);
    }



}




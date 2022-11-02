package doerr.alex.fantasyworldapi.controller;



import doerr.alex.fantasyworldapi.model.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class FantasyWorldController {

    @RequestMapping("/one" )
    public String hello(){
        //this is how I can have it print my error message to the page.
//        Person p = new Person();
//        try {
//            p.setId(-1);
//            p.setName("");
//            p.setRank("fasd");
//        } catch (Exception e) {
//            return e.getMessage();
//        }

        return "Hello from the test page 1";
    }

    @RequestMapping("/two")
    public String hello2(){
        return "Hello from the test page 2";
    }
}

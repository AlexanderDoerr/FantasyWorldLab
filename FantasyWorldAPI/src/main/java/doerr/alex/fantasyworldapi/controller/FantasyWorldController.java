package doerr.alex.fantasyworldapi.controller;



import doerr.alex.fantasyworldapi.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class FantasyWorldController {

    @Autowired
    public InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @GetMapping("/logincheck")
    public String loginCheck(){

        return "You are now logged in!";

    }
    
}

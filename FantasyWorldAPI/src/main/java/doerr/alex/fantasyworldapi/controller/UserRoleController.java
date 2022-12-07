package doerr.alex.fantasyworldapi.controller;

import doerr.alex.fantasyworldapi.BLL.UserBLL;
import doerr.alex.fantasyworldapi.model.Person;
import doerr.alex.fantasyworldapi.model.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRoleController {

    private UserBLL ub = new UserBLL();

    @Autowired
    public InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @GetMapping("")
    public List<doerr.alex.fantasyworldapi.model.User> findAllPeople() {
        System.out.println("findAllUsers");
//        return pb.findAll();
        return ub.selectUsers();
    }

    @GetMapping(path="/{id}")
    public doerr.alex.fantasyworldapi.model.User findPlanetById(@PathVariable int id) {
        System.out.println("findUserById");
        return ub.findUser(id);
    }

    @GetMapping("/checkuser/{username}")
    public String checkIfUserExist(@PathVariable("username") String username)
    {
        System.out.println(inMemoryUserDetailsManager.userExists(username) ? "User exists" : "User does not exist" );
        return inMemoryUserDetailsManager.userExists(username) ? "User exists" : "User does not exist";

    }

    @PostMapping ("/create")
    public String createUser(@RequestBody doerr.alex.fantasyworldapi.model.User user)
    {
        ub.createUser(user);
        inMemoryUserDetailsManager.createUser(org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(passwordEncoder.encode(user.getPassword())).roles(user.getRole()).build());

        return checkIfUserExist(user.getUsername());
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody doerr.alex.fantasyworldapi.model.User user)
    {
        inMemoryUserDetailsManager.updateUser(User.withUsername(user.getUsername()).password(passwordEncoder.encode(user.getPassword())).roles(user.getRole()).build());
        ub.updateUser(user);
        return checkIfUserExist(user.getUsername());
    }

    @DeleteMapping("/delete/{username}{id}")
    public String deleteUser(@PathVariable("username") String username, @PathVariable("id") int id)
    {
        inMemoryUserDetailsManager.deleteUser(username);
        ub.deleteUser(id);
        return checkIfUserExist(username);
    }




}

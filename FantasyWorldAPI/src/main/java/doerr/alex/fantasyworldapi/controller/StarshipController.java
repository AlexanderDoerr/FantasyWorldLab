package doerr.alex.fantasyworldapi.controller;

import doerr.alex.fantasyworldapi.BLL.StarshipBLL;
import doerr.alex.fantasyworldapi.model.Starship;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/starship")
public class StarshipController {

    private StarshipBLL sb = new StarshipBLL();

    @RequestMapping(path="", method=RequestMethod.POST)
    public void createStarship(@RequestBody Starship starship) {
        sb.createStarship(starship);
    }

    @RequestMapping(path="/{id}", method=RequestMethod.PUT)
    public void updateStarship(@RequestBody Starship starship) {
        sb.updateStarship(starship);
    }

    @RequestMapping(path="/{id}", method=RequestMethod.GET)
    public Starship findStarshipById(@PathVariable int id) {
        System.out.println("findStarshipById");
        return sb.findStarship(id);
    }

    @RequestMapping(path="", method=RequestMethod.GET)
    public List<Starship> findAllStarships() {
        System.out.println("findAllStarships");
        return sb.selectStarships();
    }

    @RequestMapping(path="/{id}", method=RequestMethod.DELETE)
    public void deleteStarshipById(@PathVariable int id) {
        sb.deleteStarship(id);
    }

}

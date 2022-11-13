package doerr.alex.fantasyworldapi.controller;

import doerr.alex.fantasyworldapi.BLL.PlanetBLL;
import doerr.alex.fantasyworldapi.model.Planet;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planet")
public class PlanetController {

    private PlanetBLL pb = new PlanetBLL();

    @RequestMapping(path="", method=RequestMethod.POST)
    public void createPlanet(@RequestBody Planet planet) {
        pb.createPlanet(planet);
    }

    @RequestMapping(path="/{id}", method=RequestMethod.PUT)
    public void updatePlanet(@RequestBody Planet planet) {
        pb.updatePlanet(planet);
    }

    @RequestMapping(path="/{id}", method=RequestMethod.GET)
    public Planet findPlanetById(@PathVariable int id) {
        System.out.println("findPlanetById");
        return pb.findPlanet(id);
    }

    @RequestMapping(path="", method=RequestMethod.GET)
    public List<Planet> findAllPlanets() {
        System.out.printf("findAllPlanets");
        return pb.selectPlanet();
    }

    @RequestMapping(path="/{id}", method=RequestMethod.DELETE)
    public void deletePlanetById(@PathVariable int id) {
        pb.deletePlanet(id);
    }



}

package doerr.alex.fantasyworldapi.BLL;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import doerr.alex.fantasyworldapi.model.Person;
import doerr.alex.fantasyworldapi.model.Planet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlanetBLL {

    private final File file = new File("planet.json");

    public void save(List<Planet> planets) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(file, planets);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Planet planet) {
        List<Planet> planets = findAll();
        planets.add(planet);
        save(planets);
    }

    //update person
    public void update(Planet planet) {
        List<Planet> planets = findAll();
        for (int i = 0; i < planets.size(); i++) {
            if (planets.get(i).getId() == planet.getId()) {
                planets.set(i, planet);
                save(planets);
                return;
            }
        }
        throw new RuntimeException("Planet not found");
    }

    public Planet findById(int id) {
        List<Planet> planets = findAll();
        for (Planet planet : planets) {
            if (planet.getId() == id) {
                return planet;
            }
        }
        return null;
    }

    public void delete(int id) {
        List<Planet> planets = findAll();
        for (Planet planet : planets) {
            if (planet.getId() == id) {
                planets.remove(planet);
                save(planets);
                return;
            }
        }
        throw new RuntimeException("Planet not found");
    }
    public List<Planet> findAll() {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();// use this if using less common types like LocalDateTime
        try {
            List<Planet> planets = mapper.readValue(file, new TypeReference<List<Planet>>() {
            });
            System.out.println(planets.toString());
            return planets;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package doerr.alex.fantasyworldapi.BLL;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import doerr.alex.fantasyworldapi.model.Person;
import doerr.alex.fantasyworldapi.model.Starship;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StarshipBLL {

    private final File file = new File("starship.json");

    public void save(List<Starship> starships) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(file, starships);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Starship starship) {
        List<Starship> starships = findAll();
        starships.add(starship);
        save(starships);
    }

    //update person
    public void update(Starship starship) {
        List<Starship> starships = findAll();
        for (int i = 0; i < starships.size(); i++) {
            if (starships.get(i).getId() == starship.getId()) {
                starships.set(i, starship);
                save(starships);
                return;
            }
        }
        throw new RuntimeException("Starship not found");
    }

    public Starship findById(int id) {
        return findAll().stream().filter(starship -> starship.getId() == id).findFirst().orElse(null);
    }

    public void delete(int id) {
        List<Starship> starships = findAll();
        for (int i = 0; i < starships.size(); i++) {
            if (starships.get(i).getId() == id) {
                starships.remove(i);
                save(starships);
                return;
            }
        }
        throw new RuntimeException("Starship not found");
    }
    public List<Starship> findAll() {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();// use this if using less common types like LocalDateTime
        try {
            List<Starship> starships = mapper.readValue(file, new TypeReference<List<Starship>>() {
            });
            System.out.println(starships.toString());
            return starships;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

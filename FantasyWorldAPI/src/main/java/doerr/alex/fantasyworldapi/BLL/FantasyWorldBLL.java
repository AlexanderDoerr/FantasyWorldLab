//package doerr.alex.fantasyworldapi.BLL;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import doerr.alex.fantasyworldapi.model.Person;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FantasyWorldBLL {
//
//    public void save(List<Object> objects, File file) {
//        ObjectMapper mapper = new ObjectMapper();
//        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
//        try {
//            writer.writeValue(file, objects);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void add(Object object, File file) {
//        List<Object> objects = findAll(file);
//        objects.add(object);
//        save(objects, file);
//    }
//
//    //update person
//    public void update(Object object, File file) {
//        List<Object> objects = findAll(file);
//        for (int i = 0; i < objects.size(); i++) {
//            if (objects. == person.getId()) {
//                people.set(i, person);
//                save(people);
//                return;
//            }
//        }
//        throw new RuntimeException("Person not found");
//    }
//
//    public Person findById(int id) {
//        return findAll().stream().filter(e -> e.getId() == id).findFirst().orElse(null);
//    }
//
//    public void delete(int id) {
//        List<Person> people = findAll();
//        for (int i = 0; i < people.size(); i++) {
//            if (people.get(i).getId() == id) {
//                people.remove(i);
//                save(people);
//                return;
//            }
//        }
//        throw new RuntimeException("Person not found");
//    }
//    public List<Object> findAll(File file) {
//        if (!file.exists()) {
//            return new ArrayList<>();
//        }
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.findAndRegisterModules();// use this if using less common types like LocalDateTime
//        try {
//            List<Object> objects = mapper.readValue(file, new TypeReference<List<Object>>() {
//            });
//            System.out.println(objects.toString());
//            return objects;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}


//This is a test class
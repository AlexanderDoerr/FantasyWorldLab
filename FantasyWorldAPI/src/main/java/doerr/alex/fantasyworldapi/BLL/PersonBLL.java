package doerr.alex.fantasyworldapi.BLL;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import doerr.alex.fantasyworldapi.model.Person;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonBLL {

    static String url = "jdbc:mysql://localhost:3306/fantasyworld?allowedPublicKeyRetrieval=true&useSSL=false";

    static String user = "root";
    static String password = "Test";

    public void createPerson(Person person) {
        String sql = "INSERT INTO `fantasyworld`.`person` (`name`, `rank`) VALUES (?, ?)";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, person.getName());
            pst.setString(2, person.getRank());
            pst.executeUpdate();


            System.out.println(person.getName() + " was created");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePerson(Person person) {
        String sql = "UPDATE `fantasyworld`.`person` SET `name` = ?, `rank` = ? WHERE (`id` = ?)";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, person.getName());
            pst.setString(2, person.getRank());
            pst.setInt(3, person.getId());
            pst.executeUpdate();
            System.out.println(person.getName() + " was updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Person> selectPeople() {
        String sql = "SELECT * FROM fantasyworld.person";
        List<Person> people = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String rank = rs.getString("rank");
                Person person = new Person(id, name, rank);
                people.add(person);
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("rank"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person findPerson(int id) {
        String sql = "SELECT * FROM fantasyworld.person where id=(?)";
        Person person = null;

        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int personId = rs.getInt("id");
                String name = rs.getString("name");
                String rank = rs.getString("rank");
                person = new Person(personId, name, rank);
                System.out.println("Here is the found entry" + rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("rank"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Here is what is getting returend: " + person.getId() + " " + person.getName() + " " + person.getRank());
        return person;
    }

    public void deletePerson(int id) {
        String sql = "DELETE FROM `fantasyworld`.`person` WHERE (`id` = ?)";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Person with id " + id + " was deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}

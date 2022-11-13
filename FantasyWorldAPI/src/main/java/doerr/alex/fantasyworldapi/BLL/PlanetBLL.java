package doerr.alex.fantasyworldapi.BLL;
import doerr.alex.fantasyworldapi.model.Planet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlanetBLL {
    static String url = "jdbc:mysql://localhost:3306/fantasyworld?allowedPublicKeyRetrieval=true&useSSL=false";

    static String user = "root";
    static String password = "Test";

    public void createPlanet(Planet planet) {
        String sql = "INSERT INTO `fantasyworld`.`planet` (`name`, `climate`) VALUES (?, ?)";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, planet.getName());
            pst.setString(2, planet.getClimate());
            pst.executeUpdate();


            System.out.println(planet.getName() + " was created");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePlanet(Planet planet) {
        String sql = "UPDATE `fantasyworld`.`planet` SET `name` = ?, `climate` = ? WHERE (`id` = ?)";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, planet.getName());
            pst.setString(2, planet.getClimate());
            pst.setInt(3, planet.getId());
            pst.executeUpdate();
            System.out.println(planet.getName() + " was updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Planet> selectPlanet() {
        String sql = "SELECT * FROM fantasyworld.planet";
        List<Planet> planets = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String climate = rs.getString("climate");
                Planet planet = new Planet(id, name, climate);
                planets.add(planet);
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("climate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planets;
    }

    public Planet findPlanet(int id) {
        String sql = "SELECT * FROM fantasyworld.planet where id=(?)";
        Planet planet = null;

        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int personId = rs.getInt("id");
                String name = rs.getString("name");
                String climate = rs.getString("climate");
                planet = new Planet(personId, name, climate);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return planet;

    }

    public void deletePlanet(int id) {
        String sql = "DELETE FROM `fantasyworld`.`planet` WHERE (`id` = ?)";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Planet with id " + id + " was deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

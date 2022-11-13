package doerr.alex.fantasyworldapi.BLL;


import doerr.alex.fantasyworldapi.model.Starship;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StarshipBLL {

    static String url = "jdbc:mysql://localhost:3306/fantasyworld?allowedPublicKeyRetrieval=true&useSSL=false";

    static String user = "root";
    static String password = "Test";

    public void createStarship(Starship starship) {
        String sql = "INSERT INTO `fantasyworld`.`starship` (`name`, `modelType`) VALUES (?, ?)";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, starship.getName());
            pst.setString(2, starship.getModelType());
            pst.executeUpdate();


            System.out.println(starship.getName() + " was created");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStarship(Starship starship) {
        String sql = "UPDATE `fantasyworld`.`starship` SET `name` = ?, `modelType` = ? WHERE (`id` = ?)";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, starship.getName());
            pst.setString(2, starship.getModelType());
            pst.setInt(3, starship.getId());
            pst.executeUpdate();
            System.out.println(starship.getName() + " was updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Starship> selectStarships() {
        String sql = "SELECT * FROM fantasyworld.starship";
        List<Starship> starships = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String modelType = rs.getString("modelType");
                Starship starship = new Starship(id, name, modelType);
                starships.add(starship);
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("modelType"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return starships;
    }

    public Starship findStarship(int id) {
        String sql = "SELECT * FROM fantasyworld.starship where id=(?)";
        Starship starship = null;

        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int personId = rs.getInt("id");
                String name = rs.getString("name");
                String modelType = rs.getString("modelType");
                starship = new Starship(personId, name, modelType);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return starship;
    }

    public void deleteStarship(int id) {
        String sql = "DELETE FROM `fantasyworld`.`starship` WHERE (`id` = ?)";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Starship with id " + id + " was deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

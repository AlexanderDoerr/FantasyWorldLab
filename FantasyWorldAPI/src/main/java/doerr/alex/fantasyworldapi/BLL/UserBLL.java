package doerr.alex.fantasyworldapi.BLL;

import doerr.alex.fantasyworldapi.model.Person;
import doerr.alex.fantasyworldapi.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserBLL {

        static String url = "jdbc:mysql://localhost:3306/fantasyworld?allowedPublicKeyRetrieval=true&useSSL=false";

        static String username = "root";
        static String password = "Test";

        public void createUser(User user) {
            String sql = "INSERT INTO `fantasyworld`.`user` (`username`, `password`, `role`) VALUES (?, ?, ?)";

            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, user.getUsername());
                pst.setString(2, user.getPassword());
                pst.setString(3, user.getRole());
                pst.executeUpdate();


                System.out.println(user.getUsername() + " was created");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void updateUser(User user) {
            String sql = "UPDATE `fantasyworld`.`user` SET `username` = ?, `password` = ?, `role` = ? WHERE (`id` = ?)";

            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, user.getUsername());
                pst.setString(2, user.getPassword());
                pst.setString(3, user.getRole());
                pst.setInt(4, user.getId());
                pst.executeUpdate();
                System.out.println(user.getUsername() + " was updated");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public List<User> selectUsers() {
            String sql = "SELECT * FROM fantasyworld.user";
            List<User> users = new ArrayList<>();

            try{
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement pst = connection.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    User user = new User(id, username, password, role);
                    users.add(user);
                    System.out.println(rs.getInt("id") + " " + rs.getString("username") + " " + rs.getString("password") + " " + rs.getString("role"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return users;
        }

        public User findUser(int id) {
            String sql = "SELECT * FROM fantasyworld.user where id=(?)";
            User user = null;

            try{
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int userId = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    user = new User(userId, username, password, role);
                    System.out.println("Here is the found entry" + rs.getInt("id") + " " + rs.getString("username") + " " + rs.getString("password") + " " + rs.getString("role"));
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Here is what is getting returned: " + user.getId() + " " + user.getUsername() + " " + user.getPassword() + " " + user.getRole());
            return user;
        }

        public void deleteUser(int id) {
            String sql = "DELETE FROM `fantasyworld`.`user` WHERE (`id` = ?)";

            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, id);
                pst.executeUpdate();
                System.out.println("User with id " + id + " was deleted");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}

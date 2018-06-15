package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Task;
import model.User;
import view.Dashboard;

public class TaskDao {

    public void addElement(Task task) throws MySQLIntegrityConstraintViolationException{
        String sql = "INSERT INTO `tasks`(`user`, `title`, `description`, `status`) VALUES (?, ?, ?, ?)";
        try {
          Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
          PreparedStatement add = myConn.prepareStatement(sql);
          add.setString(1, task.getUser());
          add.setString(2,task.getTitle());
          add.setString(3,task.getDescription());
          add.setString(4,task.getStatus());

          add.execute();
          add.close();
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new MySQLIntegrityConstraintViolationException ();
        } catch(Exception e) {
            e.printStackTrace();

        }
    }

    public void showElements(ObservableList<Task> data, User user) {
        String query = "";
        if(user.getUserlevel() == 9){
            query = "SELECT * FROM tasks";
        }else{
            String username = user.getUsername();
            query = "SELECT * FROM tasks WHERE user LIKE '"+ username +"'";
        }
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
            PreparedStatement add = myConn.prepareStatement(query);
            ResultSet rs = add.executeQuery();
            while(rs.next()) {
                data.add(new Task(
                        rs.getInt("id"),
                        rs.getString("user"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status")));
            }

        } catch(Exception exc) {
            exc.printStackTrace();

        }
    }

    public void updateElement(Task task) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
            PreparedStatement upd = myConn.prepareStatement("UPDATE tasks SET user = ?, title = ?, description = ?, status = ? WHERE id = ?");
            upd.setString(1, task.getUser());
            upd.setString(2, task.getTitle());
            upd.setString(3, task.getDescription());
            upd.setString(4, task.getStatus());
            upd.setInt(5, task.getId());
            upd.executeUpdate();
            upd.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void updateUserTask(Task task) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
            PreparedStatement upd = myConn.prepareStatement("UPDATE tasks SET status = ? WHERE id = ?");
            upd.setString(1, task.getStatus());
            upd.setInt(2, task.getId());
            upd.executeUpdate();
            upd.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public void deletePokemonai(int id) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
            PreparedStatement del = myConn.prepareStatement("DELETE FROM tasks WHERE id = ?");
            del.setInt(1, id);
            del.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();

        }
    }


    public ObservableList<Task> searchElementByTitle(String pavadinimas, User user){
        String sql = "";
        if (pavadinimas.isEmpty() && user.getUserlevel() == 9) {
            sql = "Select * FROM tasks";
        } else if (pavadinimas.isEmpty()) {
            sql = "Select * FROM tasks WHERE user ='"+ user.getUsername() +"'";
        } else if(!pavadinimas.isEmpty() && user.getUserlevel() == 9){
            sql = "Select * FROM tasks WHERE title LIKE '%" + pavadinimas + "%'";
        } else {
            sql = "Select * FROM tasks WHERE title LIKE '%" + pavadinimas + "%' AND user ='"+ user.getUsername() +"'";
        }


        ObservableList<Task>data = FXCollections.observableArrayList();
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
            PreparedStatement search = myConn.prepareStatement(sql);

            ResultSet rs = search.executeQuery();
            while(rs.next()) {
                data.add(new Task(
                        rs.getInt("id"),
                        rs.getString("user"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status")));
            }

        }catch(Exception exc){
            exc.printStackTrace();
        }
        return data;
    }
}

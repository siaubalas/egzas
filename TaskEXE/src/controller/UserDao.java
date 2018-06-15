package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import model.User;

public class UserDao {

    User user = new User();

    public void addUser(User user) throws MySQLIntegrityConstraintViolationException {
        String sql = "INSERT INTO `userlist`"
                + "(`username`, `password`, `userlevel`, `email`)"
                + " VALUES (?, ?, ?, ?)";
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
            PreparedStatement add = myConn.prepareStatement(sql);
            add.setString(1,user.getUsername());
            add.setString(2,user.getPassword());
            add.setInt(3,user.getUserlevel());
            add.setString(4,user.getEmail());


            add.execute();
            add.close();
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new MySQLIntegrityConstraintViolationException ();
        } catch(Exception exc){

            exc.printStackTrace();

        }
    }

    public User getUser(String username,String password){
        String sql = "SELECT * FROM userlist WHERE (username = ? AND password = ?)";

        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
            PreparedStatement pavad = myConn.prepareStatement(sql);
            pavad.setString(1,username);
            pavad.setString(2,password);
            ResultSet rs = pavad.executeQuery();
            if(rs.next()){
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUserlevel(rs.getInt("userlevel"));
                user.setEmail(rs.getString("email"));
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return user;
    }
}
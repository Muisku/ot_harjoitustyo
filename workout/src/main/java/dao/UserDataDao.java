/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author muisku
 */
public class UserDataDao implements UserDao<User, String> {
   
    private Database database;
    
    public UserDataDao(Database database) {
        this.database = database;
    }
    
    @Override
    public boolean saveOrUpdate(User object) throws SQLException {
        Connection connection = database.getConnection();

        System.out.println("New user added to database");
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User(name) VALUES(?)");

        stmt.setString(1, object.getName());
       

        stmt.executeUpdate();
        stmt.close();

        return true;
    }

    @Override
    public boolean delete(String key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findByUsername(String key) throws SQLException {
      Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE name = ?");
        stmt.setString(1, key);
        
        ResultSet rs = stmt.executeQuery();
        boolean findOne = rs.next();
       
        if (!findOne) {
            return null;
        }
        
        User user = new User(rs.getString("name"));
        
        stmt.close();
        rs.close();
        conn.close();
        
        return user;
    }
}

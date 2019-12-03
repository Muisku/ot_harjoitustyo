/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Exercise;
import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author muisku
 */
public class ExerciseDataDao implements ExerciseDao<Exercise, String> {

    private Database database;
    private UserDao<User, String> userDao;

    public ExerciseDataDao(Database database) {
        this.database = database;
    }

    @Override
    public void delete(String key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Exercise saveOrUpdate(Exercise object) throws SQLException {
        Connection connection = database.getConnection();

        Exercise exercise = findOne(object.getUser().getUsername());

        if (exercise != null) {
            return exercise;
        }

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Exercise"
                + " (user_username, exname, reps, sets, day)"
                + " VALUES (?, ?, ?, ?, ?)");

        stmt.setObject(1, object.getUser().getUsername());

        stmt.setString(2, object.getEx());
        stmt.setInt(3, object.getReps());
        stmt.setInt(4, object.getSets());
        stmt.setString(5, object.getday());
        stmt.executeUpdate();

        stmt.close();
        connection.close();

        return exercise;
    }

    @Override
    public Exercise findOne(String key) throws SQLException {
         Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Exercise WHERE id = ?");
        stmt.setString(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        User user = userDao.findByUsername(rs.getString("user_username"));

        Exercise exercise = new Exercise(rs.getInt("id"), rs.getString("day"), rs.getString("exname"), rs.getInt("reps"), rs.getInt("sets"),
                user);

        stmt.close();
        rs.close();
        connection.close();

        return exercise;
        
        
    }

    @Override
    public List<Exercise> findAll(String key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

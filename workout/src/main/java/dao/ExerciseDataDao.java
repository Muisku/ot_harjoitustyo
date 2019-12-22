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
import java.util.ArrayList;
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
                + " (user_username, exname, day, weight, sets, reps)"
                + " VALUES (?, ?, ?, ?, ?, ?)");

        stmt.setObject(1, object.getUser().getUsername());

        stmt.setString(2, object.getEx());
        stmt.setString(3, object.getDay());
        stmt.setString(4, object.getWeight());
        stmt.setString(5, object.getSets());
        stmt.setString(6, object.getReps());
//        stmt.setString(5, object.getday());
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

        Exercise exercise = new Exercise(rs.getInt("id"), rs.getString("exname"),
                user);

        stmt.close();
        rs.close();
        connection.close();

        return exercise;
        
        
    }

    @Override
    public List<Exercise> findAll(String key) throws SQLException {
        List<Exercise> exercises = new ArrayList<>();
        Connection connection = database.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Exercise WHERE user_username = ?");
        stmt.setObject(1, key);
        
         ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Exercise exercise = new Exercise(rs.getString("exName"), rs.getString("day"), rs.getString("weight"), rs.getString("sets"), rs.getString("reps"));
            exercises.add(exercise);
        }
        stmt.close();
        rs.close();
        connection.close();
        return exercises;

    }

}

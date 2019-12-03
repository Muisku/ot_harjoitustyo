/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.ExerciseDao;
import dao.ExerciseDataDao;
import dao.UserDao;
import java.sql.SQLException;
import dao.UserDao;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author muisku
 */
public class Service {
    
    private UserDao userdao;
    private User logger;
    private Exercise exercise;
    private ExerciseDao exercisedao;
    
    public Service(UserDao userdao, ExerciseDao exercisedao) {
        this.userdao = userdao;
        this.exercisedao = exercisedao;
    }
  
    public boolean ServiceCreateUser(String username, String name) throws SQLException {
        
        User user = new User(username, name);
        
        try {
            userdao.saveOrUpdate(user);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    public boolean createExercise(String exName, Integer reps, Integer sets, String day) throws SQLException {
        
        Exercise exercise = new Exercise(exName, reps, sets, day);
        exercisedao.saveOrUpdate(exercise);
        return true;
    }
    
     
     public boolean login(String name) throws SQLException {
        User user = (User) userdao.findByUsername(name);
        if (user == null) {
            return false;
        }
        logger = user;
        return true;
    }
    
     
}

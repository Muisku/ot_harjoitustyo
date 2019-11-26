/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.sql.SQLException;
import java.sql.*;
import java.util.*;

/**
 *
 * @author muisku
 */
public interface ExerciseDataDao<T, K> {
    
    void delete(K key) throws SQLException;
    
    T saveOrUpdate(T object) throws SQLException;
    
    T findOne(K key) throws SQLException;
    
    List<T> findAll(K key) throws SQLException;
    
}

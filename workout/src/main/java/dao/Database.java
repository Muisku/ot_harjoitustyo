/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author muisku
 */
public class Database {
    
    private String databaseAdress;
    
    public Database(String databaseAdress) throws ClassNotFoundException {
            this.databaseAdress = databaseAdress;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAdress);
    }
    
    public boolean init() throws SQLException {
        List<String>create_tablets = createdbTables();
        
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();
            
            for (String table : create_tablets) {
                st.executeUpdate(table);
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
            return false;
        }
        return true;
    }
    
     public List<String> createdbTables() {
        ArrayList<String> tables = new ArrayList<>();
        tables.add("CREATE TABLE IF NOT EXISTS User (id Integer PRIMARY KEY, name varchar(20));");
        
        return tables;
    }
    
}

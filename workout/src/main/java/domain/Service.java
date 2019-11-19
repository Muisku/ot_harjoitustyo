/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.UserDao;
import java.sql.SQLException;
import dao.UserDao;

/**
 *
 * @author muisku
 */
public class Service {
    
    private UserDao userdao;
    private User logger;
    
    public Service(UserDao userdao) {
        this.userdao = userdao;
    }
  
    public boolean ServiceCreateUser(String name) throws SQLException {
        
        User user = new User(name);
        
        try {
            userdao.saveOrUpdate(user);
        } catch (SQLException e) {
            return false;
        }
        return true;
    } 
}

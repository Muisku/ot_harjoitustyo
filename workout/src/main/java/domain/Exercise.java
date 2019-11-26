/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author muisku
 */
public class Exercise {
    
     int id;
     String ExName;
     public User user;
     
     public Exercise(int id, String ExName, User user) {
     
         this.id = id;
         this.ExName = ExName;
     
     }
     
     
     public Exercise(String ExName, User user) {
        
         this.ExName = ExName;
         this.user = user;
     
     }
     
     public Exercise(String ExName) {
        
         this.ExName = ExName;
      
     
     }
     
    public String getExerciseName() {
        return ExName;
    }
 

    public User getUser() {
        return user;
    }

}

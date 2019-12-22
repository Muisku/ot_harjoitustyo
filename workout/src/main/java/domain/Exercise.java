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
    public User user;
    String exName;
    String day;
    String weight;
    String reps;
    String sets;
    private boolean delete;

    public Exercise(int id, String exName, User user) {

        this.id = id;
        this.exName = exName;
        this.user = user;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.day = day;
        this.delete = false;

    }
    public Exercise(String exName, User user, String day, String weight, String reps, String sets) {

       
        this.exName = exName;
         this.user = user;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.day = day;
        this.delete = false;

    }
    
    public Exercise(String exName) {

       
        this.exName = exName;
    

    }
    public Exercise(String exName, String day, String weight, String sets, String reps) {

       
        this.exName = exName;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.day = day;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEx() {
        return exName;
    }

    public String getReps() {
        return reps;
    }
    public String getWeight() {
        return weight;
    }
    

    public String getSets() {
        return sets;
    }

    public int getId() {
        return id;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete() {
        delete = true;
    }
    public String getDay() {
        return day;
    }

    public User getUser() {
        return user;
    }

}

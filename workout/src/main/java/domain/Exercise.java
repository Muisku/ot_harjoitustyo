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
    Integer reps;
    Integer sets;
    private boolean delete;

    public Exercise(int id, String exName, String day, Integer reps, Integer sets, User user) {

        this.id = id;
        this.exName = exName;
        this.reps = reps;
        this.sets = sets;
        this.day = day;
        this.delete = false;

    }
    public Exercise(String exName, Integer reps, Integer sets, String day) {

       
        this.exName = exName;
        this.reps = reps;
        this.sets = sets;
        this.day = day;
        this.delete = false;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEx() {
        return exName;
    }

    public int getReps() {
        return reps;
    }
    

    public int getSets() {
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
    public String getday() {
        return day;
    }

    public User getUser() {
        return user;
    }

}

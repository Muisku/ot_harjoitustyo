/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

//import dao.Database;
//import dao.UserDao;
//import dao.UserDataDao;
//import domain.Service;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.Properties;
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;¨
import dao.Database;
import dao.UserDataDao;
import java.sql.*;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import domain.Service;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;

/**
 *
 * @author muisku
 */
public class workoutUi extends Application {

    public Database database;
    private Service service;
    private Scene createUserScene;
    private Scene loginscene;

    @Override
    public void init() throws IOException, Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        try {
            String usedDatabase = properties.getProperty("usedDatabase");
            database = new Database(usedDatabase);
        } catch (Exception ex) {
        }

        database.init();

        UserDataDao userDao = new UserDataDao(database);
        service = new Service(userDao);

    }

    @Override
    public void start(Stage primaryStage) {
        Button btncreateuser = new Button();
        Button btnlogin = new Button();

//        HBox newUsersBox = new HBox();
//        Button createnewUser = new Button("create");
//        TextField newUserName = new TextField();
//
//        newUsersBox.getChildren().addAll(createnewUser, newUserName);
//        
//        createUserScene = new Scene(newUsersBox, 200, 200);
        btncreateuser.setText("Create User");
        btnlogin.setText("Login");

        btncreateuser.setOnAction(e -> {                                 //CREATE BUTTON ACTION; CREATE USER SCENE

            primaryStage.setScene(createUserScene);
        });

        //createUser
        HBox components = new HBox();
        components.setSpacing(20);
        components.getChildren().addAll(btncreateuser, btnlogin);

        Scene loginscene = new Scene(components);

        primaryStage.setTitle("Create User or Login");
        primaryStage.setScene(loginscene);
        primaryStage.show();

        createUser(primaryStage);

    }

    public void createUser(Stage primaryStage) {
        HBox createBox = new HBox(15);
        Button createUserbtn = new Button("Create User");
        TextField userName = new TextField();

        createBox.getChildren().addAll(userName, createUserbtn);
        createUserScene = new Scene(createBox, 200, 200);

        createUserbtn.setOnAction(e -> {
            String name = userName.getText();
            try {
                if (service.ServiceCreateUser(name)) {
                    System.out.println("wohoo new trainer!");
                    primaryStage.setScene(loginscene);
                }
            } catch (SQLException ex) {
                Logger.getLogger(workoutUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

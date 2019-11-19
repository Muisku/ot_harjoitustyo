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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author muisku
 */
public class workoutUi extends Application {

    public Database database;
    private Service service;

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

        TextField givename = new TextField();
        TextField giveloginname = new TextField();

        btncreateuser.setText("Create User");
        btnlogin.setText("Login");

        String name = givename.getText();

//        btncreateuser.setOnAction(e -> {
//            try {
//                if (Service.createUser(name)) {
//
//                }
//
//            } catch (SQLException ex) {
//                System.out.println("Problem with");
//            }
//        });

        HBox components = new HBox();
        components.setSpacing(20);
        components.getChildren().addAll(btncreateuser, givename, btnlogin, giveloginname);

        Scene scene = new Scene(components);

        primaryStage.setTitle("Create User or Login");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

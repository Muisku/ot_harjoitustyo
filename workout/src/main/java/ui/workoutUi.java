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
import dao.ExerciseDataDao;
import dao.UserDataDao;
import java.sql.*;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import domain.Service;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import dao.ExerciseDao;
import domain.Exercise;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author muisku
 */
public class workoutUi extends Application {

    public Database database;
    private Service service;
    private Scene createUserScene;
    private Scene loginscene;
    private Scene trainingscene;
    private VBox exnode;

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
        ExerciseDataDao exerciseDao = new ExerciseDataDao(database);
        service = new Service(userDao, exerciseDao);

    }

    @Override
    public void start(Stage primaryStage) throws SQLException {

        // CREATE FIRST VIEW
        Button btncreateuser = new Button();
        Button btnlogin = new Button();
        TextField loginName = new TextField();

        HBox components = new HBox();
        components.setSpacing(20);
        components.getChildren().addAll(btncreateuser, btnlogin, loginName);

// 
        btncreateuser.setText("Create User");
        btnlogin.setText("Login");

        loginscene = new Scene(components);

        primaryStage.setTitle("Create User or Login");
        primaryStage.setScene(loginscene);

        // END FIRST VIEW
        // CREATE createUSERVIEW
        createUser(primaryStage);
        // END CREATE USER VIEW

        // CREATE LOGIN USER VIEW
        createloginUserView(primaryStage);
        // END LOGUN USER VIEW CREATION

        //CREATE USER SET UP
        btncreateuser.setOnAction(e -> {
            primaryStage.setTitle("Create User");
            primaryStage.setScene(createUserScene);
        });

        //LOGIN BUTTON SET UP
        btnlogin.setOnAction(e -> {
            String logginginName = loginName.getText();

            try {
                if (service.login(logginginName)) {
                    System.out.println("own view!");
                    primaryStage.setTitle("List Ur Trainings");
                    refreshEx();
                    primaryStage.setScene(trainingscene);
                    //       stage.setScene(trainingscene)
                }
            } catch (SQLException ex) {
                Logger.getLogger(workoutUi.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        primaryStage.show();

    }

    public void createUser(Stage primaryStage) {
        HBox createBox = new HBox(15);
        Button createUserbtn = new Button("Create User!");
        Button bcloginscreen = new Button("Back to login screen");
        TextField userName = new TextField();
        TextField Name = new TextField();

        createBox.getChildren().addAll(userName, Name, createUserbtn, bcloginscreen);
        createUserScene = new Scene(createBox, 200, 200);

        createUserbtn.setOnAction(e -> {
            String username = userName.getText();
            String name = Name.getText();
            try {
                if (service.ServiceCreateUser(username, name)) {
                    System.out.println("wohoo new trainer!");
                    primaryStage.setTitle("Create User or Login");

                    primaryStage.setScene(loginscene);
                }
            } catch (SQLException ex) {
                Logger.getLogger(workoutUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        bcloginscreen.setOnAction(e -> {
            primaryStage.setScene(loginscene);
        });

    }

    public void createloginUserView(Stage primaryStage) throws SQLException {

        TextField exname = new TextField();
        TextField day = new TextField();
        TextField weight = new TextField();
        TextField sets = new TextField();
        TextField reps = new TextField();

        Label exLabel = new Label("exercise:");
        Label dateLabel = new Label("date:");
        Label weightLabel = new Label("weight:");
        Label setsLabel = new Label("sets:");
        Label repsLabel = new Label("reps:");

        Button createtraining = new Button("createtraining");
        Button logout = new Button("logout");
        HBox createtrainingcomponents = new HBox(10);
        createtrainingcomponents.setPadding(new Insets(15, 15, 15, 15));
        HBox top = new HBox(10);
        top.setPadding(new Insets(15, 15, 15, 15));
        Region spacer = new Region();

        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox middle = new HBox(10);
        middle.setPadding(new Insets(15, 15, 15, 15));
        ScrollPane contscroll = new ScrollPane();
        BorderPane trainingpage = new BorderPane(contscroll);
        
        trainingscene = new Scene(trainingpage, 1200, 500);
        
        trainingpage.setPadding(new Insets(20, 20, 20, 20));
        exnode = new VBox(10);
        exnode.setMaxWidth(500);
        exnode.setMinWidth(500);
        contscroll.setContent(exnode);

        createtrainingcomponents.getChildren().addAll(exLabel, exname, dateLabel, day, weightLabel, weight, setsLabel, sets, repsLabel, reps, spacer);
        top.getChildren().addAll(createtraining, logout, spacer);
        middle.getChildren().addAll(contscroll, spacer);

        trainingpage.setCenter(middle);
        trainingpage.setTop(top);
        trainingpage.setBottom(createtrainingcomponents);

        

        createtraining.setOnAction(e -> {
//            int repss = Integer.parseInt(reps.getText());
//            int setss = Integer.parseInt(sets.getText());
            try {
                service.createExercise(exname.getText(), day.getText(), weight.getText(), sets.getText(), reps.getText());
                
                refreshEx();
            } catch (SQLException ex) {
                Logger.getLogger(workoutUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
          logout.setOnAction(e -> {
            primaryStage.setScene(loginscene);
        });

        refreshEx();
    }

    public void refreshEx() throws SQLException {

        exnode.getChildren().clear();
        List<Exercise> exercises;
        exercises = service.getExercises();

        exercises.forEach(content -> {
            try {
                exnode.getChildren().add(createExNode(content));
            } catch (SQLException ex) {
                System.out.println("Error with node!");
            }
        });

    }

    public Node createExNode(Exercise exercise) throws SQLException {
        HBox exbox = new HBox(10);

        Label exlabel = new Label(exercise.getEx());

        Label date = new Label(exercise.getDay());
        Label weight = new Label(exercise.getWeight());

        Label setsLabel = new Label(exercise.getSets() + " kertaa          ");

        Label repsLabel = new Label(exercise.getReps() + " toistoa          ");

        Region spacer = new Region();
        exbox.setPadding(new Insets(5, 0, 0, 20));

        exbox.getChildren().addAll(date,exlabel, weight, setsLabel, repsLabel);

        return exbox;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

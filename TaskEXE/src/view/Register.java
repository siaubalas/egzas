package view;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import controller.UserDao;
import controller.Validation;
import model.User;


public class Register {
    private Stage primaryStage;
    private BorderPane root;

    Register(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.root = new BorderPane();

        Scene scene = new Scene(this.root, 405, 270);

        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Task New Account");
        this.primaryStage.setResizable(false);
        this.primaryStage.centerOnScreen();
        scene.getStylesheets().add(getClass().getResource("../view/style.css").toExternalForm());
        addElementsToScene();
        primaryStage.show();

    }

    private void addElementsToScene (){
        Label lblUsername = new Label("Username");
        TextField tfUsername = new TextField();

        HBox hbRegisterText = new HBox();

        DropShadow dropshadow = new DropShadow();
        dropshadow.setOffsetX(5);
        dropshadow.setOffsetY(5);


        Label lblEmail = new Label("Email");
        TextField tfEmail = new TextField();

        Label lblPassword = new Label("Password");
        PasswordField pfPassword = new PasswordField();
        Label lblPassword1 = new Label("Repeat Password");
        PasswordField pfPassword1 = new PasswordField();
        Button btnRegister = new Button("Register");
        Button btnBack = new Button("Back");
        btnRegister.setMinWidth(100);
        btnBack.setMinWidth(100);


        GridPane gpRegister = new GridPane();
        gpRegister.add(lblUsername,0,0);
        gpRegister.add(tfUsername,1,0);
        gpRegister.add(lblEmail,0,1);
        gpRegister.add(tfEmail,1,1);
        gpRegister.add(lblPassword,0,2);
        gpRegister.add(pfPassword,1,2);
        gpRegister.add(lblPassword1,0,3);
        gpRegister.add(pfPassword1,1,3);
        gpRegister.add(btnRegister, 1, 4);
        gpRegister.add(btnBack, 1, 5);
        gpRegister.setPadding(new Insets(10,10,10,10));
        gpRegister.setAlignment(Pos.CENTER);
        gpRegister.setVgap(10);
        gpRegister.setHgap(10);

        this.root.setTop(hbRegisterText);
        this.root.setCenter(gpRegister);



        btnRegister.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                if(!Validation.isValidCredentials(tfUsername.getText().toString())){
                    showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Form Klaida!", "Invalid name");
                    return;
                }
                if(!Validation.isValidEmail(tfEmail.getText().toString())){
                    showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Form Klaida!", "Invalid Email");
                    return;
                }
                if(!Validation.isValidCredentials(pfPassword.getText().toString())){
                    showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Form Klaida!", "Invalid password");
                    return;
                }
                if(!pfPassword.getText().toString().equals(pfPassword1.getText().toString())){
                    showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Form Klaida!", "Password doesn't match");
                    return;
                }
                try {
                    showAlert(Alert.AlertType.INFORMATION, gpRegister.getScene().getWindow(), "Success, ", "Hello " + tfUsername.getText().toString());
                    User user = new User(tfUsername.getText().toString(),pfPassword.getText().toString(),1,tfEmail.getText().toString());
                    UserDao userDao = new UserDao();
                    userDao.addUser(user);
                    Login login = new Login(primaryStage);
                } catch (com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException e) {
                    showAlert(Alert.AlertType.INFORMATION, gpRegister.getScene().getWindow(), "Form Info!", "This email is registered");
                }
            }
        });

        btnBack.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Login login = new Login(primaryStage);
            }
        });

        gpRegister.setId("background");
    }
    private void showAlert(Alert.AlertType alerType, Window owner, String title, String message){
        Alert alert = new Alert(alerType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}


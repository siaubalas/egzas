package view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import controller.UserDao;
import controller.Validation;
import model.User;


public class Login {

    private Stage primaryStage;
    private BorderPane bpRoot;
    private GridPane gpLogin;

    public Login(Stage primaryStage) {
        this.primaryStage = primaryStage;

        addElementsToScene();
        primaryStage.show();
    }

    private void addElementsToScene() {
        primaryStage.setTitle("Task Login");
        this.bpRoot = new BorderPane();
        Scene scene = new Scene(this.bpRoot, 330, 270);
        scene.getStylesheets().add(getClass().getResource("../view/style.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

        Label lblUsername = new Label("Username");
        TextField tfUsername = new TextField();
        Label lblPassword = new Label("Password");
        PasswordField pfPassword = new PasswordField();

        Button btnLogin = new Button("Log in");
        btnLogin.setMinWidth(100);
        btnLogin.setAlignment(Pos.CENTER);

        Button btnRegister = new Button("New Account");
        btnRegister.setMinWidth(100);
        btnRegister.setAlignment(Pos.CENTER);

        Label lblMessage = new Label ();
        btnRegister.setOnAction((ActionEvent e)->{
            Register register = new Register(this.primaryStage);
        });

        btnLogin.setOnAction((ActionEvent e)->{
            UserDao userDao = new UserDao();
            User user = userDao.getUser(tfUsername.getText().toString(), pfPassword.getText().toString());
            if (user.getUsername() != null && user.getUserlevel() == 1) {
                Dashboard dashBoard = new Dashboard(this.primaryStage, user);
            }else if (user.getUsername() != null && user.getUserlevel() == 9) {
                AdminDashboard adminDashboard = new AdminDashboard(this.primaryStage, user);
            }else if(!Validation.isValidCredentials(tfUsername.getText().toString())){
                showAlert(Alert.AlertType.ERROR, gpLogin.getScene().getWindow(), "Form Klaida!", "Invalid username");
            }else if(!Validation.isValidCredentials(pfPassword.getText().toString())){
                showAlert(Alert.AlertType.ERROR, gpLogin.getScene().getWindow(), "Form Klaida!", "Invalid password");
            }else {
                showAlert(Alert.AlertType.ERROR, gpLogin.getScene().getWindow(), "Form Klaida!", "No such user!");
            }

        });

        HBox hbLoginText = new HBox();

        DropShadow dropshadow = new DropShadow();
        dropshadow.setOffsetX(5);
        dropshadow.setOffsetY(5);


        this.gpLogin = new GridPane();
        gpLogin.add(lblUsername,0,1);
        gpLogin.add(tfUsername,1,1);
        gpLogin.add(lblPassword,0,2);
        gpLogin.add(pfPassword,1,2);
        gpLogin.add(lblMessage, 1, 3);
        gpLogin.add(btnLogin, 1, 4);
        gpLogin.add(btnRegister, 1, 5);
        gpLogin.setPadding(new Insets(10,10,10,10));
        gpLogin.setAlignment(Pos.CENTER);
        gpLogin.setVgap(10);
        gpLogin.setHgap(10);

        Reflection reflection = new Reflection();
        reflection.setFraction(1f);
        gpLogin.setEffect(reflection);


        bpRoot.setTop(hbLoginText);
        bpRoot.setCenter(gpLogin);
        primaryStage.setScene(scene);
        primaryStage.show();


        gpLogin.setId("background");
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

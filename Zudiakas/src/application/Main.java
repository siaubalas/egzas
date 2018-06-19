package application;



import javafx.application.Application;
import javafx.stage.Stage;
import view.DashB;



public class Main extends Application {
    Stage primaryStage;
    @Override
    public void start(Stage primaryStage) {
        DashB admindashboard = new DashB(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
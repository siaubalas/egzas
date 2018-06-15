package view;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import controller.TaskDao;
import controller.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Task;
import model.User;

public class Dashboard {

    private BorderPane bpRoot;
    private Stage primaryStage;
    private User user;
    private GridPane DashboardPane2;
    private TextField tfId;
    private RadioButton rbToDo;
    private RadioButton rbDone;
    private ToggleGroup tgStatus;
    private RadioButton selectedRadioButton;



    Dashboard(Stage primaryStage, User user) {
        this.bpRoot = new BorderPane();
        Scene scene = new Scene(this.bpRoot, 980, 400);

        this.primaryStage = primaryStage;
        this.user = user;
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Task system");
        scene.getStylesheets().add(getClass().getResource("../view/style.css").toExternalForm());
        this.primaryStage.setResizable(false);
        this.primaryStage.centerOnScreen();
        addElementsToScene();
        this.primaryStage.show();
    }


	private void addElementsToScene() {
		Label lbId = new Label("Id: ");
        Label lbStatus = new Label("Status: ");
        Label lbUsername = new Label("Logged In");
        lbUsername.setMinWidth(100);
        Label lbUsernameCurrent = new Label();
        lbUsernameCurrent.setText(user.getUsername());

        this.tfId = new TextField();

        this.rbToDo = new RadioButton();
        this.rbDone = new RadioButton();
        this.tgStatus = new ToggleGroup();

        rbDone.setText("Done ");
        rbToDo.setText("To Do ");

        rbToDo.setToggleGroup(tgStatus);
        rbDone.setToggleGroup(tgStatus);
        rbToDo.setSelected(true);


        Button btnUpdate = new Button("Update");
        btnUpdate.setMinWidth(80);

        Button btnLog = new Button("Log out");
        btnLog.setMinWidth(80);


        TableView ItemTable = new TableView();
        ItemTable.setEditable(true);

        TableColumn IdCol = new TableColumn("id");
        IdCol.setCellValueFactory(new PropertyValueFactory<Task, Integer>("id"));
        IdCol.setMinWidth(20);

        TableColumn UserCol = new TableColumn("User");
        UserCol.setCellValueFactory(new PropertyValueFactory<Task, String>("user"));
        UserCol.setMinWidth(80);

        TableColumn TitleCol = new TableColumn("Title");
        TitleCol.setCellValueFactory(new PropertyValueFactory<Task, String>("title"));
        TitleCol.setMinWidth(100);

        TableColumn DescriptionCol = new TableColumn("Description");
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<Task, Integer>("description"));
        DescriptionCol.setMinWidth(40);

        TableColumn StatusCol = new TableColumn("Status");
        StatusCol.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));
        StatusCol.setMinWidth(60);

            ItemTable.getColumns().addAll(IdCol, TitleCol, DescriptionCol, StatusCol);


        ObservableList<Task> data = FXCollections.observableArrayList();
        TaskDao TaskDao = new TaskDao();
        TaskDao.showElements(data, user);
        ItemTable.setItems(data);

        GridPane gpItemButtons = new GridPane();
        gpItemButtons.add(btnLog, 4, 0);


        GridPane gpStatus = new GridPane();
        gpStatus.add(rbToDo, 1, 1);
        gpStatus.add(rbDone, 3, 1);
        this.DashboardPane2 = new GridPane();
        DashboardPane2.add(lbUsername, 0, 0);
        DashboardPane2.add(lbUsernameCurrent, 1, 0);
        DashboardPane2.add(lbId, 0, 6);
        DashboardPane2.add(tfId, 1, 6);
        DashboardPane2.add(btnUpdate, 2, 6);
        DashboardPane2.add(lbStatus, 0, 2);
        DashboardPane2.add(gpStatus, 1, 2);
        DashboardPane2.add(btnLog, 2, 8);
        DashboardPane2.setPadding(new Insets(10, 10, 10, 10));
        DashboardPane2.setVgap(10);
        DashboardPane2.setHgap(10);
        bpRoot.setCenter(DashboardPane2);
        bpRoot.setRight(ItemTable);
        DashboardPane2.setMinWidth(400);
        ItemTable.setPrefWidth(560);

        
        btnUpdate.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
            	 if(runescape_validate("update")) {
                    int id = 0 ;
                    Task kvepalas = new Task(
                            Integer.parseInt(tfId.getText().toString()),
                            selectedRadioButton.getText().toString());

                    boolean IsExistingEntryIdByUser = false;

                    for(int i = 0 ;i<data.size();i++)
                    {
                        if(data.get(i).getId() == Integer.parseInt(tfId.getText()) )
                        {

                            IsExistingEntryIdByUser = true;
                            TaskDao.updateUserTask(kvepalas);
                            ItemTable.getItems().clear();
                            TaskDao.showElements(data, user);

                        }
                    }

                    if (!IsExistingEntryIdByUser) {
                        showAlert(Alert.AlertType.ERROR, DashboardPane2.getScene().getWindow(), "Form Klaida!", "Toks id neegzistuoja");
                    }

            	 }
            }
        });


        btnLog.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Login login = new Login(primaryStage);
            }
        });


        btnUpdate.setId("button");
        DashboardPane2.setId("background");
    }

    private boolean runescape_validate(String action) {
        switch(action) {
            case "delete":
                if(!Validation.isValidID(tfId.getText().toString())){
                    showAlert(Alert.AlertType.ERROR, DashboardPane2.getScene().getWindow(), "Form Klaida!", "Neteisingas ID formatas");
                    return false;
                }
                else return true;

            default:
          
                    this.selectedRadioButton = (RadioButton)tgStatus.getSelectedToggle();
                
        }
        return true;
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
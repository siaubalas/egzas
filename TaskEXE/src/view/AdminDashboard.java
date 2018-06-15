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

public class AdminDashboard {

    private BorderPane bpRoot;
    private Stage primaryStage;
    private User user;
    private GridPane DashboardPane2;
    private TextField tfId;
    private TextField tfUser;
    private TextField tfTitle;
    private TextArea tfDescription;
    private RadioButton rbToDo;
    private RadioButton rbDone;
    private ToggleGroup tgStatus;
    private RadioButton selectedRadioButton;



    AdminDashboard(Stage primaryStage, User user) {
        this.bpRoot = new BorderPane();
        Scene scene = new Scene(this.bpRoot, 980, 400);

        this.primaryStage = primaryStage;
        this.user = user;
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Task Admin System");
        scene.getStylesheets().add(getClass().getResource("../view/style.css").toExternalForm());
        this.primaryStage.setResizable(false);
        this.primaryStage.centerOnScreen();
        addElementsToScene();
        this.primaryStage.show();
    }


	private void addElementsToScene() {
		Label lbId = new Label("Id: ");
        Label lbUser = new Label("User: ");
        Label lbTitle = new Label("Title: ");
        Label lbDescription = new Label("Description: ");
        Label lbStatus = new Label("Status: ");
        Label lbUsername = new Label("Logged In");
        lbUsername.setMinWidth(80);
        Label lbUsernameCurrent = new Label();
        lbUsernameCurrent.setText(user.getUsername());

	this.tfId = new TextField();
        this.tfUser = new TextField();
        this.tfTitle = new TextField();
        this.tfDescription = new TextArea();
        tfDescription.setPrefHeight(60);
        this.rbToDo = new RadioButton();
        this.rbDone = new RadioButton();
        this.tgStatus = new ToggleGroup();

        rbDone.setText("Done ");
        rbToDo.setText("To Do ");

        rbToDo.setToggleGroup(tgStatus);
        rbDone.setToggleGroup(tgStatus);
        rbToDo.setSelected(true);

        Button btnAdd = new Button("Add");
        btnAdd.setMinWidth(60);
        Button btnDelete = new Button("Delete");
        btnDelete.setMinWidth(80);
        Button btnUpdate = new Button("Update");
        btnUpdate.setMinWidth(80);
        Button btnSearch = new Button("Search");
        btnSearch.setMinWidth(80);
        Button btnLog = new Button("Log out");
        btnLog.setMinWidth(80);


        TableView ItemTable = new TableView();
        ItemTable.setEditable(true);

        TableColumn IdCol = new TableColumn("id");
        IdCol.setCellValueFactory(new PropertyValueFactory<Task, Integer>("id"));
        IdCol.setMinWidth(10);

        TableColumn UserCol = new TableColumn("User");
        UserCol.setCellValueFactory(new PropertyValueFactory<Task, String>("user"));
        UserCol.setMinWidth(50);

        TableColumn TitleCol = new TableColumn("Title");
        TitleCol.setCellValueFactory(new PropertyValueFactory<Task, String>("title"));
        TitleCol.setMinWidth(100);

        TableColumn DescriptionCol = new TableColumn("Description");
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<Task, Integer>("description"));
        DescriptionCol.setMinWidth(40);

        TableColumn StatusCol = new TableColumn("Status");
        StatusCol.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));
        StatusCol.setMinWidth(40);

        ItemTable.getColumns().addAll(IdCol, UserCol, TitleCol,StatusCol, DescriptionCol);

        ObservableList<Task> data = FXCollections.observableArrayList();
        TaskDao TaskDao = new TaskDao();
        TaskDao.showElements(data, user);
        ItemTable.setItems(data);

        GridPane gpItemButtons = new GridPane();
        gpItemButtons.add(tfId, 0, 0);
        gpItemButtons.add(btnDelete, 2, 0);
        tfId.setMaxWidth(80);
        gpItemButtons.setVgap(20);
        gpItemButtons.setHgap(20);
        gpItemButtons.setPadding(new Insets(10, 10, 10, 0));
        
        GridPane gpStatus = new GridPane();
        gpStatus.add(rbToDo, 1, 1);
        gpStatus.add(rbDone, 3, 1);
        
        this.DashboardPane2 = new GridPane();
        DashboardPane2.add(lbUsername, 0, 0);
        DashboardPane2.add(lbUsernameCurrent, 1, 0);
        DashboardPane2.add(lbId, 0, 8);
        DashboardPane2.add(gpItemButtons, 1, 8);
        DashboardPane2.add(btnUpdate, 2, 8);
        DashboardPane2.add(lbUser, 0, 2);
        DashboardPane2.add(tfUser, 1, 2);
        DashboardPane2.add(lbTitle, 0, 3);
        DashboardPane2.add(tfTitle, 1, 3);
        DashboardPane2.add(btnSearch, 2, 3);
        DashboardPane2.add(lbDescription, 0, 5);
        DashboardPane2.add(tfDescription, 1, 5);
        DashboardPane2.add(lbStatus, 0, 6);
        DashboardPane2.add(gpStatus, 1, 6);


        DashboardPane2.add(btnAdd, 1, 7);
        DashboardPane2.add(btnLog, 2, 10);
        DashboardPane2.setPadding(new Insets(10, 10, 10, 10));
        DashboardPane2.setVgap(10);
        DashboardPane2.setHgap(10);
        bpRoot.setCenter(DashboardPane2);
        bpRoot.setRight(ItemTable);
        DashboardPane2.setMinWidth(400);
        ItemTable.setPrefWidth(700);

        btnAdd.setOnAction(new EventHandler<ActionEvent>(){


            @Override
            public void handle(ActionEvent event){
                if(runescape_validate("add"))
                {
                    Task uzduotis = new Task(
                            //Integer.parseInt(tfId.getText().toString()),
                            tfUser.getText().toString(),
                            tfTitle.getText().toString(),
                            tfDescription.getText().toString(),
                            selectedRadioButton.getText().toString());
                    try {
                        TaskDao.addElement(uzduotis);
                    } catch (MySQLIntegrityConstraintViolationException e) {
                        showAlert(Alert.AlertType.INFORMATION, DashboardPane2.getScene().getWindow(), "Form Info!", "ka tu ismanai");
                    }

                    ItemTable.getItems().clear();

                    TaskDao.showElements(data, user);
                }
            }

        });
        btnDelete.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event){
                runescape_validate("delete");

                boolean IsExistingEntryIdByUser = false;

                for(int i = 0 ;i < data.size(); i++)
                {
                    if(data.get(i).getId() == Integer.parseInt(tfId.getText()) )
                    {
                        IsExistingEntryIdByUser = true;

                        TaskDao.deletePokemonai(Integer.parseInt(tfId.getText()));
                        ItemTable.getItems().clear();
                        TaskDao.showElements(data, user);
                    }
                }

                if (!IsExistingEntryIdByUser) {
                    showAlert(Alert.AlertType.ERROR, DashboardPane2.getScene().getWindow(), "Form Klaida!", "Toks id neegzistuoja");
                }
            }
        });

        btnLog.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Login login = new Login(primaryStage);
            }
        });

        btnSearch.setOnAction(new EventHandler<ActionEvent>(){
            private ObservableList<Task> data;
            @Override
            public void handle(ActionEvent event){
                data = TaskDao.searchElementByTitle(tfTitle.getText().toString(),user);
                ItemTable.getItems().clear();
                ItemTable.setItems(data);

                if (data.size() == 0) {
                    showAlert(Alert.AlertType.ERROR, DashboardPane2.getScene().getWindow(), "Form Klaida!", "No such task!");
                }
            }
        });



        btnUpdate.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                if(runescape_validate("update")) {
                    int id = 0 ;
                    Task kvepalas = new Task(
                            Integer.parseInt(tfId.getText().toString()),
                            tfUser.getText().toString(),
                            tfTitle.getText().toString(),
                            tfDescription.getText().toString(),
                            selectedRadioButton.getText().toString());

                    boolean IsExistingEntryIdByUser = false;

                    for(int i = 0 ;i<data.size();i++)
                    {
                        if(data.get(i).getId() == Integer.parseInt(tfId.getText()) )
                        {

                            IsExistingEntryIdByUser = true;
                            TaskDao.updateElement(kvepalas);
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

        btnSearch.setOnAction(new EventHandler<ActionEvent>(){
            private ObservableList<Task> data;
            @Override
            public void handle(ActionEvent event){
//                data = ItemDao.searchElementsByName
//                        (RsnText.getText().toString(), user);
//                table.getItems().clear();
//                table.setItems(data);
                data = TaskDao.searchElementByTitle(tfTitle.getText().toString(),user);
                ItemTable.getItems().clear();
                ItemTable.setItems(data);

                if (data.size() == 0) {
                    showAlert(Alert.AlertType.ERROR, DashboardPane2.getScene().getWindow(), "Form Klaida!", "no such task!");
                }
            }
        });

        btnLog.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Login login = new Login(primaryStage);
            }
        });
        btnAdd.setId("button");
        btnDelete.setId("button");
        btnSearch.setId("button");

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
               /* if(!Validation.isValidID(tfId.getText().toString())){
                    showAlert(Alert.AlertType.ERROR, DashboardPane2.getScene().getWindow(), "Form Klaida!", "Neteisingas ID formatas");
                    return false;
                } else */if (!Validation.isValidRsnForAdd(tfUser.getText().toString())) {
                    showAlert(Alert.AlertType.ERROR, DashboardPane2.getScene().getWindow(), "Form Klaida!", "Invalid User");
                    return false;
                } else if (!Validation.isValidRsnForAdd(tfTitle.getText().toString())) {
                    showAlert(Alert.AlertType.ERROR, DashboardPane2.getScene().getWindow(), "Form Klaida!", "Invalid Task");
                    return false;
                } else if(!Validation.isValidRsnForAdd(tfDescription.getText().toString())){
                    showAlert(Alert.AlertType.ERROR, DashboardPane2.getScene().getWindow(), "Form Klaida!", "Invalid Description");
                    return false;
                }else {
                    this.selectedRadioButton = (RadioButton)tgStatus.getSelectedToggle();
                }
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
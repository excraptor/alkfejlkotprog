package kotprog.View;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kotprog.Controller.UserController;
import kotprog.Model.UserModel;

public class AdminView extends Stage {

    private TableView<UserModel> usersTable = new TableView<>();

    private UserController userController = UserController.getInstance();
    
    public AdminView(){
        usersScene();
    }

    public void constructAdminView() {
        setTitle("Admin panel");
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));

        Label admin = new Label("Admin");
        layout.add(admin, 0, 0);

        


        //TODO list all users and groups, maybe in a table, property stuff, delete button
    }

    public void usersScene() {
        TableColumn<UserModel, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("nick"));

        TableColumn<UserModel, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        TableColumn<UserModel, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        
        TableColumn<UserModel, Integer> ageColumn = new TableColumn<>("Username");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<UserModel, String> interest1Column = new TableColumn<>("Username");
        interest1Column.setCellValueFactory(new PropertyValueFactory<>("nick"));

        TableColumn<UserModel, String> interest2Column = new TableColumn<>("Username");
        interest2Column.setCellValueFactory(new PropertyValueFactory<>("nick"));

        usersTable.getColumns().addAll(usernameColumn, passwordColumn, genderColumn, ageColumn, interest1Column, interest2Column);

        List<UserModel> usersList = userController.getAllUsers();
        ObservableList<UserModel> users = FXCollections.observableArrayList(usersList);
        this.usersTable.setItems(users);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(usersTable);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        VBox root = new VBox();
        root.getChildren().add(scrollPane);

        Scene scene = new Scene(root, 800, 800);
        this.setScene(scene);
        this.show();
    }
}
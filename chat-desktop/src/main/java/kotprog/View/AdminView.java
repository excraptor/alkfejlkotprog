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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import kotprog.Controller.GroupController;
import kotprog.Controller.UserController;
import kotprog.Model.ChatgroupModel;
import kotprog.Model.UserModel;

public class AdminView extends Stage {

    private TableView<UserModel> usersTable;
    private TableView<ChatgroupModel> groupsTable;

    private UserController userController = UserController.getInstance();
    private GroupController groupController = GroupController.getInstance();
    
    public AdminView(){
        constructAdminView();
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

        Button manageUsers = new Button("Manage users");
        manageUsers.setOnAction(e -> {
            usersScene();
        });

        Button manageGroups = new Button("Manage groups");
        manageGroups.setOnAction(e -> {
            groupScene();
        });

        layout.add(manageUsers, 0, 1);
        layout.add(manageGroups, 1, 1);

        Scene scene = new Scene(layout, 800, 800);
        setScene(scene);
        show();

        
        //TODO add buttons
        //TODO ON DELETE CASCADE
        //TODO list all users and groups, maybe in a table, property stuff, delete button
    }

    public void usersScene() {
        usersTable = new TableView<>();
        TableColumn<UserModel, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("nick"));

        TableColumn<UserModel, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        TableColumn<UserModel, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        
        TableColumn<UserModel, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<UserModel, String> interest1Column = new TableColumn<>("Interest1");
        interest1Column.setCellValueFactory(new PropertyValueFactory<>("interest1"));

        TableColumn<UserModel, String> interest2Column = new TableColumn<>("Interest2");
        interest2Column.setCellValueFactory(new PropertyValueFactory<>("interest2"));


        usersTable.getColumns().addAll(usernameColumn, passwordColumn, genderColumn, ageColumn, interest1Column, interest2Column);
        
        addButtonToUsersTable();        

        List<UserModel> usersList = userController.getAllUsers();
        ObservableList<UserModel> users = FXCollections.observableArrayList(usersList);
        this.usersTable.setItems(users);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(usersTable);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        VBox root = new VBox();
        root.getChildren().add(scrollPane);

        Button back = new Button("Back to controls");
        back.setOnAction(e -> {
            constructAdminView();
            usersTable = null;
        });
        root.getChildren().add(back);

        Scene scene = new Scene(root, 800, 800);
        this.setScene(scene);
        this.show();
    }
    public void groupScene() {
        groupsTable = new TableView<>();
        TableColumn<ChatgroupModel, String> nameColumn = new TableColumn<>();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ChatgroupModel, String> themeColumn = new TableColumn<>();
        themeColumn.setCellValueFactory(new PropertyValueFactory<>("theme"));

        groupsTable.getColumns().addAll(nameColumn, themeColumn);

        addButtonToGroupsTable();

        List<ChatgroupModel> groups = groupController.getAllChatgroups();
        this.groupsTable.setItems(FXCollections.observableArrayList(groups));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(groupsTable);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        VBox root = new VBox();
        root.getChildren().add(scrollPane);
        
        Button back = new Button("Back to controls");
        back.setOnAction(e -> {
            constructAdminView();
            groupsTable = null;
        });
        root.getChildren().add(back);

        Scene scene = new Scene(root, 800, 800);
        this.setScene(scene);
        this.show();
    }

    private void addButtonToUsersTable() {
        TableColumn<UserModel, Void> columnButton = new TableColumn<>();
        Callback<TableColumn<UserModel, Void>, TableCell<UserModel, Void>> cellFactory = new Callback<TableColumn<UserModel,Void>,TableCell<UserModel,Void>>() {

            @Override
            public TableCell<UserModel, Void> call(TableColumn<UserModel, Void> param) {
                final TableCell<UserModel, Void> cell = new TableCell<UserModel, Void>(){
                    private final Button button = new Button("Delete");
                    {
                        button.setOnAction(e -> {
                            userController.deleteUser(usersTable.getItems().get(getIndex()).getNick());
                            refreshUsersTable();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
                return cell;
            }
        };

        columnButton.setCellFactory(cellFactory);
        usersTable.getColumns().add(columnButton);
    }
    //could've done better... maybe next time
    private void addButtonToGroupsTable() {
        TableColumn<ChatgroupModel, Void> columnButton = new TableColumn<>();
        Callback<TableColumn<ChatgroupModel, Void>, TableCell<ChatgroupModel, Void>> cellFactory = new Callback<TableColumn<ChatgroupModel,Void>,TableCell<ChatgroupModel,Void>>() {

            @Override
            public TableCell<ChatgroupModel, Void> call(TableColumn<ChatgroupModel, Void> param) {
                final TableCell<ChatgroupModel, Void> cell = new TableCell<ChatgroupModel, Void>(){
                    private final Button button = new Button("Delete");
                    {
                        button.setOnAction(e -> {
                            groupController.deleteGroup(groupsTable.getItems().get(getIndex()).getName());
                            refreshGroupsTable();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
                return cell;
            }
        };

        columnButton.setCellFactory(cellFactory);
        groupsTable.getColumns().add(columnButton);
    }

    

    public void refreshUsersTable() {
        List<UserModel> list = userController.getAllUsers();
        usersTable.setItems(FXCollections.observableList(list));
    }
    public void refreshGroupsTable() {
        List<ChatgroupModel> list = groupController.getAllChatgroups();
        groupsTable.setItems(FXCollections.observableList(list));
    }
}
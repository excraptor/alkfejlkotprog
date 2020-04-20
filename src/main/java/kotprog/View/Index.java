package kotprog.View;

import java.util.List;

import javafx.concurrent.Task;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;
import kotprog.Controller.GroupController;
import kotprog.Controller.UserController;
import kotprog.Model.*;

public class Index {

    private static UserController userController = UserController.getInstance();
    private static GroupController groupController = GroupController.getInstance();
    //private UserModel currentUser;

    public static Scene constructIndexScene(LaunchView mainWindow) {
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));

        Text welcomeTxt = new Text("Better than Coospace");
        welcomeTxt.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
        layout.add(welcomeTxt, 0, 0);

        Button logout = new Button("Log out");
        layout.add(logout, 6, 0);
        logout.setOnAction(e -> {
            mainWindow.constructLaunchView(userController);
            mainWindow.setCurrentUser(null);
        });
        Button newGroup = new Button("Make a new group");
        layout.add(newGroup, 6, 1);
        newGroup.setOnAction(e -> {
            Stage newGroupStage = new NewGroupDialog();
        });

        Text currentUserText = new Text(mainWindow.getCurrentUser().toString());
        layout.add(currentUserText, 0, 1);

        VBox groupContainer = new VBox();
        layout.add(groupContainer, 0, 2);
        List<ChatgroupModel> chatgroups = groupController.getAllChatgroups();
        for (ChatgroupModel chatgroupModel : chatgroups) {
            Text groupName = new Text(chatgroupModel.getName());
            groupName.setFont(Font.font("Tahoma", FontWeight.LIGHT, 20));
            groupContainer.getChildren().add(groupName);
            Text theme = new Text(chatgroupModel.getTheme());
            groupContainer.getChildren().add(theme);
            
            groupName.setOnMouseClicked(e -> {
                ClientView client = new ClientView(chatgroupModel, mainWindow.getCurrentUser());
                
            });

            
        }



        return new Scene(layout, 540, 640);
    }
}
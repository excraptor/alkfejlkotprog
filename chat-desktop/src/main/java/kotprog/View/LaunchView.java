package kotprog.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kotprog.Controller.UserController;
import kotprog.Model.ChatgroupModel;
import kotprog.Model.UserModel;

public class LaunchView extends Stage{

    private UserModel currentUser;
    private ChatgroupModel cg;
    private static UserController controller = UserController.getInstance();
    public void setCurrentUser(UserModel user) {
        this.currentUser = user;
    }
    public UserModel getCurrentUser() {
        return this.currentUser;
    }

    public ChatgroupModel getCurrentGroup() {
        return this.cg;
    }

    public void setCurrentGroup(ChatgroupModel cg) {
        this.cg = cg;
    }


    public LaunchView () {
        constructLaunchView(controller);
        show();
    }

    public void constructLaunchView(UserController controller) {
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));
        
        Text welcomeTxt = new Text("Welcome");
        welcomeTxt.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
        layout.add(welcomeTxt, 0, 0);

        Button login = new Button("Login");
        layout.add(login, 0, 1);
        login.setOnAction(e -> {
            AuthView loginStage = new AuthView(0, this);
        });

        Button register = new Button("Register");
        layout.add(register, 1, 1);
        register.setOnAction(e -> {
            AuthView registerStage = new AuthView(1, this);
        });
        /*Button user = new Button("test current user");
        layout.add(user, 0, 2);
        user.setOnAction(e -> {
            System.out.println(currentUser);
        });*/
        Scene scene = new Scene(layout, 540, 640);
        setScene(scene);
        setTitle("helo frendz");
        show();
    }
}
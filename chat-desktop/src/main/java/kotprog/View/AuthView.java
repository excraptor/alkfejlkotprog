package kotprog.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kotprog.Controller.GroupController;
import kotprog.Controller.UserController;
import kotprog.Model.UserModel;
import kotprog.Utils.Utils;

public class AuthView extends Stage{

    private static UserController controller = UserController.getInstance();
    private GroupController groupController = GroupController.getInstance();
    private UserModel currentUser;
    private LaunchView mainWindow;

    public  UserModel getCurrentUser() {
            return this.currentUser;
    }

    public AuthView(int which, LaunchView mainWindow) {
        if (which == 1) {
            constructRegistrationScene();
        } else {
            setScene(loginScene());
        }
        this.mainWindow = mainWindow;
        show();
    }

    public void constructRegistrationScene(){
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));
        
        Text welcomeTxt = new Text("Register");
        welcomeTxt.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
        layout.add(welcomeTxt, 0, 0);

        Label labelUser = new Label("Username");
        layout.add(labelUser, 0, 1);
        TextField textUser = new TextField();
        textUser.setPromptText("Username");
        layout.add(textUser, 1, 1);

        Label labelPassword = new Label("Password");
        layout.add(labelPassword, 0, 2);
        PasswordField textPassword = new PasswordField();
        textPassword.setPromptText("Password");
        layout.add(textPassword, 1, 2);

        Label labelGender = new Label("Gender");
        layout.add(labelGender, 0, 3);
        TextField textGender = new TextField();
        textGender.setPromptText("Gender");
        layout.add(textGender, 1, 3);

        Label labelAge = new Label("Age");
        layout.add(labelAge, 0, 4);
        TextField textAge = new TextField();
        textAge.setPromptText("Age");
        layout.add(textAge, 1, 4);
        
        Label labelInterest1 = new Label("Interest1");
        layout.add(labelInterest1, 0, 5);
        TextField textInterest1 = new TextField();
        textInterest1.setPromptText("Interest1");
        layout.add(textInterest1, 1, 5);
        
        Label labelInterest2 = new Label("Interest2");
        layout.add(labelInterest2, 0, 6);
        TextField textInterest2 = new TextField();
        textInterest2.setPromptText("Interest2");
        layout.add(textInterest2, 1, 6);
        Button regButton = new Button("Register");
        layout.add(regButton, 1, 7);
        regButton.setOnAction(e -> {
            if(textUser.getText().contentEquals("")) {
                Utils.showWarning("Username can't be blank");
                return;
            };
            if(textPassword.getText().length() < 3) {
                Utils.showWarning("Password must contain at least 3 characters");
                return;
            };
            if(textGender.getText().contentEquals("")) {
                Utils.showWarning("There are infinite genders, please at least choose one of them");
                return;
            };
            int ev;
            try  {
                ev = Integer.parseInt(textAge.getText());
            } catch(NumberFormatException nfe) {
                Utils.showWarning("Age is not a number");
                return;
            }
            if(textInterest1.getText().contentEquals("")) {
                Utils.showWarning("Interest can't be blank");
            };
            if(textInterest2.getText().contentEquals("")) {
                Utils.showWarning("Interest can't be blank");
                return;
            };
            UserModel user = new UserModel(textUser.getText(),textPassword.getText(),textGender.getText(),Integer.parseInt(textAge.getText()),textInterest1.getText(),textInterest2.getText());
            
            if(controller.registerUser(user)) {
                // move to login page
                Scene loginScene = loginScene();
                setScene(loginScene);
            }  else {
                Utils.showWarning("Saving was unsuccesful, please try again");
            }
        });
        
        Scene scene = new Scene(layout, 540, 640);
        setScene(scene);
        setTitle("helo frendz");
        show();
        
    }

    public Scene loginScene() {
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));

        Text welcomeTxt = new Text("Login");
        welcomeTxt.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
        layout.add(welcomeTxt, 0, 0);

        Label labelUser = new Label("Username");
        layout.add(labelUser, 0, 1);
        TextField textUser = new TextField();
        textUser.setPromptText("Username");
        layout.add(textUser, 1, 1);

        Label labelPassword = new Label("Password");
        layout.add(labelPassword, 0, 2);
        PasswordField textPassword = new PasswordField();
        textPassword.setPromptText("Password");
        layout.add(textPassword, 1, 2);

        Button login = new Button("Login");
        layout.add(login, 1,3);
        login.setOnAction(e -> {
            if(textUser.getText().equals("admin") && textPassword.getText().equals("admin")){
                AdminView admin = new AdminView();
                close();
            } else {
                currentUser = controller.login(textUser.getText(), textPassword.getText());
                if (currentUser != null) {
                    
                    mainWindow.setCurrentUser(currentUser);
                    mainWindow.setScene(Index.constructIndexScene(mainWindow));
                    close();
                } else {
                    Utils.showWarning("Wrong username or password");
                }
            }
        });
        return new Scene(layout);
    }
    
}
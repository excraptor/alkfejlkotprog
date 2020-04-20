package kotprog;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kotprog.Controller.UserController;
import kotprog.Model.UserModel;
import kotprog.View.AuthView;
import kotprog.View.LaunchView;

public class App extends Application {
    private Stage mainWindow;
    
    @Override
    public void start(Stage stage) throws Exception {
        //temporary solution
        mainWindow = new LaunchView();
        mainWindow.setTitle("hello frendz");
        mainWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
        
    }

}
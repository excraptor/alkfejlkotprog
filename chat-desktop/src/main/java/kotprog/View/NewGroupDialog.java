package kotprog.View;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kotprog.Controller.GroupController;

public class NewGroupDialog extends Stage {
    GroupController controller = GroupController.getInstance();
    public NewGroupDialog () {
        constructNewDialog();
    }

    public void constructNewDialog() {
        setTitle("Make a new group");
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));

        Text titleTxt = new Text("Make a new group");
        titleTxt.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
        layout.add(titleTxt, 0, 0);

        Label groupName = new Label("Name: ");
        layout.add(groupName, 0, 1);
        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Group name");
        layout.add(nameTextField, 1, 1);

        Label groupTheme = new Label("Theme: ");
        layout.add(groupTheme, 0, 2);
        TextField themeTextField = new TextField();
        themeTextField.setPromptText("Theme");
        layout.add(themeTextField, 1, 2);

        Button submit = new Button("Add");
        layout.add(submit, 1, 3);
        submit.setOnAction(e -> {
            controller.newGroup(nameTextField.getText(), themeTextField.getText());
            close();
        });

        Scene scene = new Scene(layout, 540, 200);
        setScene(scene);
        show();
    }
}
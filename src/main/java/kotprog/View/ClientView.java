package kotprog.View;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kotprog.Controller.GroupController;
import kotprog.Controller.MessageController;
import kotprog.Controller.UserController;
import kotprog.Model.ChatgroupModel;
import kotprog.Model.MessageModel;
import kotprog.Model.UserModel;
import kotprog.Utils.ConnectionUtil;
import kotprog.Utils.TaskReadThread;

/**
 *
 * @author topman garbuja,
 *
 *         This is the client which passes and get message to and from server
 *         and further to multiple clients
 *
 *         It also uses TaskReadThread.java file to be used in a new thread in
 *         order to get simultaneous input from server
 */
public class ClientView extends Stage {

    // controls

    TextField txtInput;
    ScrollPane scrollPane;
    public TextArea txtAreaDisplay;
    UserController userController = UserController.getInstance();
    GroupController groupController = GroupController.getInstance();
    MessageController messageController = MessageController.getInstance();
    private UserModel currentUser;
    private ChatgroupModel currentGroup;
    private List<MessageModel> messages;

    public ChatgroupModel getCurrentGroup() {
        return this.currentGroup;
    }

    public void setCurrentGroup(ChatgroupModel currentGroup) {
        this.currentGroup = currentGroup;

    }

    public UserModel getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }

    // IO streams

    public ClientView(ChatgroupModel currentGroup, UserModel currentUser) {

        this.currentGroup = currentGroup;
        this.currentUser = currentUser;
        constructClientView();
    }

    public void constructClientView() {
        // test

        // pane to hold scroll pane and HBox
        VBox vBox = new VBox();

        scrollPane = new ScrollPane(); // pane to display text messages
        HBox hBox = new HBox(); // pane to hold input textfield and send button

        txtAreaDisplay = new TextArea();
        txtAreaDisplay.setEditable(false);
        scrollPane.setContent(txtAreaDisplay);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        messages = messageController.getMessagesFromGroup(currentGroup.getName());
        for (MessageModel messageModel : messages) {
            txtAreaDisplay.appendText("[" + messageModel.getUserNick() + "]: " + messageModel.getMessage() + "");
        }
        // define textfield and button and add to hBox

        txtInput = new TextField();
        txtInput.setPromptText("New message");
        txtInput.setTooltip(new Tooltip("Write your message. "));
        Button btnSend = new Button("Send");
        btnSend.setOnAction(e -> {
            System.out.println(currentUser.getNick());
            String userName = currentUser.getNick();
            String message = txtInput.getText().trim();
            if (message.length() == 0) {
                return;
            }
            txtAreaDisplay.appendText("[" + userName + "]: " + message + "\n");
            messageController.sendMessage(new MessageModel(message,userName, getCurrentGroup().getName()));
            txtInput.clear();
        });

        hBox.getChildren().addAll(txtInput, btnSend);
        HBox.setHgrow(txtInput, Priority.ALWAYS); // set textfield to grow as window size grows

        //set center and bottom of the borderPane with scrollPane and hBox
        vBox.getChildren().addAll(scrollPane, hBox);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        //create a scene and display
        Scene scene = new Scene(vBox, 450, 500);
        setTitle("Client: JavaFx Text Chat App");
        setScene(scene);
        show();

        
    }
}
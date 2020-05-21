package kotprog.View;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kotprog.Controller.GroupController;
import kotprog.Controller.MessageController;
import kotprog.Controller.UserController;
import kotprog.Model.ChatgroupModel;
import kotprog.Model.MessageModel;
import kotprog.Model.UserModel;
import kotprog.Utils.ConnectionUtil;

public class ClientView extends Stage {

    // controls

    TextField txtInput;
    ScrollPane scrollPane;
    public VBox messageDisplayArea;
    UserController userController = UserController.getInstance();
    GroupController groupController = GroupController.getInstance();
    MessageController messageController = MessageController.getInstance();
    private UserModel currentUser;
    private ChatgroupModel currentGroup;
    private List<MessageModel> messages;
    private File currentImage;
    private String currentImageString;

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

        messageDisplayArea = new VBox();

        scrollPane.setContent(messageDisplayArea);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        messages = messageController.getMessagesFromGroup(currentGroup.getName());
        for (MessageModel messageModel : messages) {
            if(messageModel.isImage() == 0) {
                messageDisplayArea.getChildren()
                    .add(new Label("[" + messageModel.getUserNick() + "]: " + messageModel.getMessage() + ""));
            } else {
                byte[] decodedBytes = Base64.getDecoder().decode(messageModel.getMessage());
                try {
                    File temp = new File("temp.png");
                    FileUtils.writeByteArrayToFile(temp, decodedBytes);
                    Image image = new Image(temp.toURI().toString());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(490);
                    imageView.setFitWidth(450);
                    imageView.setPreserveRatio(true);
                    messageDisplayArea.getChildren().add(new Label("[" + messageModel.getUserNick() + "]:"));
                    messageDisplayArea.getChildren().add(imageView);
                    temp.delete();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            
        }
        // define filechooser to be able to send messages
        final FileChooser fileChooser = new FileChooser();
        final Button openButton = new Button("Choose image");
        openButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(this);
            this.currentImage = file;
            try {
                byte[] fileContent = FileUtils.readFileToByteArray(file);
                String encodedString = Base64.getEncoder().encodeToString(fileContent);
                this.currentImageString = encodedString;
                System.out.println("file selected");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        // define textfield and button and add to hBox
        txtInput = new TextField();
        txtInput.setPromptText("New message");
        txtInput.setTooltip(new Tooltip("Write your message. "));
        Button btnSend = new Button("Send");
        btnSend.setOnAction(e -> {
            String userName = currentUser.getNick();
            String message = txtInput.getText().trim();
            if (message.length() == 0 && currentImage == null) {
                return;
            }
            if(message.length() > 0) {
                messageDisplayArea.getChildren().add(new Label("[" + userName + "]: " + message + ""));
                messageController.sendMessage(new MessageModel(message, userName, getCurrentGroup().getName(), 0));
            }
            if (this.currentImage != null) {
                Image image = new Image(this.currentImage.toURI().toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(490);
                imageView.setFitWidth(450);
                imageView.setPreserveRatio(true);
                messageDisplayArea.getChildren().add(imageView);
                messageController.sendMessage(new MessageModel(currentImageString, userName, getCurrentGroup().getName(), 1));
                this.currentImage = null;
                this.currentImageString = null;
            }
            
            txtInput.clear();
        });

        hBox.getChildren().addAll(txtInput, btnSend, openButton);
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
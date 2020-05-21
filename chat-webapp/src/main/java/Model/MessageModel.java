package Model;

import java.io.Serializable;
import java.util.*;

public class MessageModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private String userNick;
    private String roomName;
    private int image = 0;

    public MessageModel (String message, String userNick, String roomName, int image) {
        this.message = message;
        this.userNick = userNick;
        this.roomName = roomName;
        this.image = image;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    

    public String getUserNick() {
        return this.userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public void setImage(int isImage) {
        this.image = isImage;
    }
    public int isImage() {
        return this.image;
    }

}
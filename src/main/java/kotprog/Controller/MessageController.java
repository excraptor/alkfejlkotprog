package kotprog.Controller;

import java.util.List;

import kotprog.DAO.ChatDAO;
import kotprog.DAO.ChatDAOInterface;
import kotprog.Model.MessageModel;

public class MessageController {
    
    private ChatDAOInterface dao = ChatDAO.getInstance();
    private static MessageController single_instance;

    private MessageController() {

    }

    public static MessageController getInstance(){
        if(single_instance == null) {
            single_instance = new MessageController();
        }
        return single_instance;
    }

    public boolean sendMessage(MessageModel msg) {
        return dao.addMessage(msg);
    }

    public List<MessageModel> getMessagesFromGroup(String groupName) {
        return dao.getMessagesFromGroup(groupName);
    }
}
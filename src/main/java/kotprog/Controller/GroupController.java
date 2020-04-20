package kotprog.Controller;

import java.util.List;

import kotprog.DAO.ChatDAO;
import kotprog.DAO.ChatDAOInterface;
import kotprog.Model.ChatgroupModel;

public class GroupController {
    ChatDAOInterface dao = ChatDAO.getInstance();
    private static GroupController single_instance = null;
    private ChatgroupModel currentGroup;

    private GroupController() {

    }
    public static GroupController getInstance() {
        if(single_instance == null) {
            single_instance = new GroupController();
        }
        return single_instance;
    }
    public boolean newGroup(String name, String theme) {
        return dao.newGroup(name, theme);
    }

    public List<ChatgroupModel> getAllChatgroups() {
        return dao.getAllChatgroups();
    }

    public ChatgroupModel getCurrentGroup() {
        return this.currentGroup;
    }
    public GroupController setCurrentGroup(ChatgroupModel cg) {
        this.currentGroup = cg;
        return this;
    }
}
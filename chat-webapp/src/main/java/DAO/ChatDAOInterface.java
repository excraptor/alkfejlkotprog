package DAO;

import java.util.List;

import Model.*;

public interface ChatDAOInterface {
    public boolean registerUser(UserModel user);
    public UserModel login(String username, String password);
    public List<ChatgroupModel> getAllChatgroups();
    public boolean newGroup(String name, String theme);
    public boolean addMessage(MessageModel msg);
    public List<MessageModel> getMessagesFromGroup(String groupName);
    public List<UserModel> getAllUsers();
    public boolean deleteUser(String nick);
    public boolean deleteGroup(String name);
    public List<ChatgroupModel> selectFromGroups(String search, String searchcategory);
    public List<UserModel> selectFromUsers(String search, String searchcategory);
}
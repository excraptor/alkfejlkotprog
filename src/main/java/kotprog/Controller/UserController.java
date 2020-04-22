package kotprog.Controller;

import java.util.List;

import kotprog.DAO.*;
import kotprog.Model.*;

public class UserController {
    private ChatDAOInterface dao = ChatDAO.getInstance();
    private static UserController single_instance;
    private UserModel currentUser;

    private UserController() {
        
    }

    public static UserController getInstance() {
        if(single_instance == null) {
            single_instance = new UserController();
        }
        return single_instance;
    }


    public boolean registerUser(UserModel user) {
        return dao.registerUser(user);
    }
     
    public UserModel login(String username, String password) {
        return dao.login(username, password);
    }
    public UserModel getCurrentUser() {
        return this.currentUser;
    }

    public List<UserModel> getAllUsers() {
        //threads!!
        return dao.getAllUsers();
    }

    

    
}
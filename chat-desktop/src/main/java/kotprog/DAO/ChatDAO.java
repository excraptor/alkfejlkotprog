package kotprog.DAO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kotprog.Model.ChatgroupModel;
import kotprog.Model.MessageModel;
import kotprog.Model.UserModel;

public class ChatDAO implements ChatDAOInterface {
    private static ChatDAO single_instance;
    private final static String DB_STRING = "jdbc:sqlite:/itt/modositsd/a/tomcat/utvonalat/apache-tomcat-9.0.34/bin/chat.db";

    private static final String CREATE_USER = "PRAGMA foreign_key=ON;" +
    
    "CREATE TABLE IF NOT EXISTS User (" + "nick text PRIMARY KEY,"
            + "password text NOT NULL," + "gender text NOT NULL," + "age integer NOT NULL," + "interest1 text NOT NULL,"
            + "interest2 text NOT NULL);";
    private static final String CREATE_GROUP = "CREATE TABLE IF NOT EXISTS GroupChat ("
    + "name text PRIMARY KEY," 
    + "theme text NOT NULL);";

    private static final String CREATE_MSG = "CREATE TABLE IF NOT EXISTS Message ("
    + "message text NOT NULL,"
    + "sent text NOT NULL,"
    + "user_nick text NOT NULL,"
    + "group_name text NOT NULL,"
    + "is_image integer DEFAULT 0,"
    + "PRIMARY KEY (sent, user_nick, group_name, message),"
    + "FOREIGN KEY (user_nick) REFERENCES User(nick) ON DELETE CASCADE," 
    + "FOREIGN KEY (group_name) REFERENCES GroupChat(name) ON DELETE CASCADE);";
    //private static final String CREATE_MSG1 = " ALTER TABLE Message ADD PRIMARY KEY (sent, user_nick, group_name, message);";

    

    private static final String INSERT_USER = "INSERT INTO User (nick, password, gender, age,"
            + "interest1, interest2) VALUES (?,?,?,?,?,?);";


    public void initializeTables() {
        try (Connection conn = DriverManager.getConnection(DB_STRING); Statement st = conn.createStatement()) {
            st.executeUpdate(CREATE_USER);
            st.executeUpdate(CREATE_GROUP);
            st.executeUpdate(CREATE_MSG);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private ChatDAO() {
        initializeTables();
    }
    public static ChatDAO getInstance() {
        if(single_instance == null) {
            single_instance = new ChatDAO();
        }
        return single_instance;
    }

    

    

    @Override
    public boolean registerUser(UserModel user) {
        try (Connection conn = DriverManager.getConnection(DB_STRING);
                PreparedStatement st = conn.prepareStatement(INSERT_USER)) {
            st.setString(1, user.getNick());
            st.setString(2, user.getPassword());
            st.setString(3, user.getGender());
            st.setInt(4, user.getAge());
            st.setString(5, user.getInterest1());
            st.setString(6, user.getInterest2());
            int res = st.executeUpdate();
            if (res == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static final String LOGIN_USER = "SELECT * FROM User WHERE nick=?";

    @Override
    public UserModel login(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_STRING);
                PreparedStatement st = conn.prepareStatement(LOGIN_USER);) {
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("nincs ilyen user");
                return null;
            }
            UserModel user = new UserModel();
            user.setPassword(rs.getString(2));
            if (password.equals(user.getPassword())) {
                System.out.println("jo a jelszo");
                user.setNick(username);
                user.setAge(rs.getInt(4));
                user.setGender(rs.getString(3));
                user.setInterest1(rs.getString(5));
                user.setInterest2(rs.getString(6));

                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final String SELECT_ALL_GROUPS = "SELECT * FROM GroupChat";
    @Override
    public List<ChatgroupModel> getAllChatgroups() {
        List<ChatgroupModel> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_STRING);
        Statement st = conn.createStatement();
        ) {
            ResultSet rs = st.executeQuery(SELECT_ALL_GROUPS);
            while(rs.next()) {
                ChatgroupModel chgm = new ChatgroupModel(
                    rs.getString(1),
                    rs.getString(2) 
                    );
                    result.add(chgm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static final String NEW_GROUP = "INSERT INTO GroupChat (name, theme) VALUES(?,?);";
    @Override
    public boolean newGroup(String name, String theme) {
        try (Connection conn = DriverManager.getConnection(DB_STRING);
            PreparedStatement st = conn.prepareStatement(NEW_GROUP);
        ) {
            st.setString(1, name);
            st.setString(2, theme);
            int res = st.executeUpdate();
            if(res == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static final String INSERT_MSG = "INSERT INTO Message" +
    "(message, sent, user_nick, group_name, is_image) VALUES (?,datetime('now', 'localtime'),?,?,?)";
    @Override
    public boolean addMessage(MessageModel msg) {
        try (Connection conn = DriverManager.getConnection(DB_STRING);
        PreparedStatement st = conn.prepareStatement(INSERT_MSG);
        ) {
            st.setString(1, msg.getMessage());
            st.setString(2, msg.getUserNick());
            st.setString(3, msg.getRoomName());
            st.setInt(4, msg.isImage());
            int res = st.executeUpdate();
            if(res == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static final String SELECT_MSG_FROM_GROUP = "SELECT * FROM Message WHERE group_name = ? ORDER BY sent";    
    @Override
    public List<MessageModel> getMessagesFromGroup(String groupName) {

        List<MessageModel> messages = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_STRING);
        PreparedStatement st = conn.prepareStatement(SELECT_MSG_FROM_GROUP);
        ) {
            st.setString(1, groupName);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                try {
                    messages.add(new MessageModel(rs.getString(1), rs.getString(3), rs.getString(4), rs.getInt(5)));
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String GET_ALL_USERS = "SELECT * FROM User";
    @Override
    public List<UserModel> getAllUsers() {
        List<UserModel> users = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_STRING);
            Statement sql = conn.createStatement();
        ) {
            ResultSet rs = sql.executeQuery(GET_ALL_USERS);
            while(rs.next()) {
                users.add(new UserModel(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getString(6)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return users;
    }

    private static final String DELETE_USER = "DELETE FROM User WHERE nick = ?";
    @Override
    public boolean deleteUser(String nick) {
        try (Connection conn = DriverManager.getConnection(DB_STRING);
            PreparedStatement st = conn.prepareStatement(DELETE_USER);
        ) {
            st.setString(1, nick);
            int res = st.executeUpdate();
            if(res == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static final String DELETE_GROUP = "DELETE FROM GroupChat WHERE name = ?";
    @Override
    public boolean deleteGroup(String name) {
        try (Connection conn = DriverManager.getConnection(DB_STRING);
            PreparedStatement st = conn.prepareStatement(DELETE_GROUP);
        ) {
            st.setString(1, name);
            int res = st.executeUpdate();
            if(res == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    

}

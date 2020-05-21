package Controller;

import DAO.ChatDAO;
import DAO.ChatDAOInterface;
import Model.MessageModel;
import Model.UserModel;
import org.apache.commons.io.FileUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@WebServlet("/ChatController")
public class ChatController extends HttpServlet {

    private ChatDAOInterface dao = ChatDAO.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String groupname = (String) request.getSession().getAttribute("groupname");
        System.out.println(((UserModel) request.getSession().getAttribute("currentUser")).getNick());
        request.setAttribute("messages", dao.getMessagesFromGroup(groupname));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = request.getParameter("messageSent");
        String user = ((UserModel) request.getSession().getAttribute("currentUser")).getNick();
        String group = (String) request.getSession().getAttribute("groupname");
        boolean ret = dao.addMessage(new MessageModel(message, user, group, 0));
        System.out.println(message);
        request.setCharacterEncoding("UTF-8");
        if (ret) {
            response.sendRedirect("pages/chat.jsp");
        }
    }
}

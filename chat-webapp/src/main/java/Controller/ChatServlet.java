package Controller;

import DAO.ChatDAO;
import DAO.ChatDAOInterface;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {

    private ChatDAOInterface dao = ChatDAO.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String groupName = request.getParameter("groupname");
        //request.setAttribute("messages", dao.getMessagesFromGroup(groupName));
        HttpSession session = request.getSession();
        session.setAttribute("groupname", groupName);
        response.sendRedirect("pages/chat.jsp");

    }
}

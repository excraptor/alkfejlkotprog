package Controller;

import DAO.ChatDAO;
import DAO.ChatDAOInterface;
import Model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
    private ChatDAOInterface dao = ChatDAO.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        String name = request.getParameter("name");
        String category = request.getParameter("category");

        boolean ret = dao.newGroup(name, category);
        if (ret) {
            response.sendRedirect("pages/home.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UserModel user = (UserModel) request.getSession().getAttribute("currentUser");
        request.setAttribute("groupList", dao.getAllChatgroups());
    }
}

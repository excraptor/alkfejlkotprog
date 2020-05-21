package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.UserModel;
import DAO.ChatDAO;
import DAO.ChatDAOInterface;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("pages/login.jsp");
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        
        ChatDAOInterface dao = ChatDAO.getInstance();

        String username = request.getParameter("username");
        String password = request.getParameter("userpassword");

        UserModel user = dao.login(username, password);
        
        if(user == null) {
            response.sendRedirect("pages/login.jsp");
            return;
        }

        request.getSession().setAttribute("currentUser", user);
        response.sendRedirect("pages/home.jsp");
    }


    
}
package Controller;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.UserModel;
import DAO.ChatDAO;
import DAO.ChatDAOInterface;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("pages/register.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChatDAOInterface dao = ChatDAO.getInstance();

        String username = request.getParameter("username");
        String password = request.getParameter("userpassword");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String interest1 = request.getParameter("interest1");
        String interest2 = request.getParameter("interest2");

        UserModel user = new UserModel(username, password, gender, age, interest1, interest2);

        boolean ret = dao.registerUser(user);
        if(ret) {
            response.sendRedirect("pages/login.jsp");
        }else {
            response.sendRedirect("pages/register.jsp");
        }
        
    }

    
}
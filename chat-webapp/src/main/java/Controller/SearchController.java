package Controller;

import DAO.ChatDAO;
import DAO.ChatDAOInterface;
import Model.ChatgroupModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
    private ChatDAOInterface dao = ChatDAO.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String search = request.getParameter("search");
        String searchcategory = request.getParameter("categories");

        HttpSession session = request.getSession();
        session.setAttribute("search", search);
        session.setAttribute("searchcategory", searchcategory);
        if (searchcategory.equals("username") || searchcategory.equals("userinterests")) {
            response.sendRedirect("pages/usersearch.jsp");
        } else {
            response.sendRedirect("pages/groupsearch.jsp");
        }


    }
}

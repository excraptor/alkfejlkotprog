package Controller;

import DAO.ChatDAO;
import DAO.ChatDAOInterface;
import Model.ChatgroupModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchManager")
public class SearchManager extends HttpServlet {

    private ChatDAOInterface dao = ChatDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String search = (String) session.getAttribute("search");
        String searchcategory = (String) session.getAttribute("searchcategory");

        if (searchcategory.equals("groupname") || searchcategory.equals("groupcategory")) {
            req.setAttribute("searchResult", dao.selectFromGroups(search, searchcategory));
        }
        if (searchcategory.equals("username") || searchcategory.equals("userinterests")) {
            req.setAttribute("searchResult", dao.selectFromUsers(search, searchcategory));
        }

    }
}

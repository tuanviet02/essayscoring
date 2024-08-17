
package Controller;

import DAO.LevelDAO;
import Model.Level;
import Model.Pagination;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "SearchLevelController", urlPatterns = {"/SearchLevel"})
public class SearchLevelController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchLevelController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchLevelController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchByName = request.getParameter("searchByName");
        LevelDAO levelDao = new LevelDAO();
        List<Level> levelList = levelDao.searchLevelsByName(searchByName);

        // Sort the search result if sortField parameter is present
        // Pagination
        int limitPage = 2;
        if (request.getParameter("cp") == null) {
            Pagination<Level> pagination = new Pagination<>(levelList, limitPage, 1);
            levelList = pagination.getItemsOnPage();
            request.getSession().setAttribute("page", pagination);
        } else {
            int currentPage = Integer.parseInt(request.getParameter("cp"));
            Pagination<Level> pagination = new Pagination<>(levelList, limitPage, currentPage);
            levelList = pagination.getItemsOnPage();
            request.getSession().setAttribute("page", pagination);
        }
 request.setAttribute("pagging", "SearchLevel?searchByName="  + searchByName +"&");
        request.setAttribute("tList", levelList);
        request.getRequestDispatcher("manageLevel.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

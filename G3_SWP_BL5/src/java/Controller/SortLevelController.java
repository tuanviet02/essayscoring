
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
import java.util.List;


@WebServlet(name = "SortLevelController", urlPatterns = {"/SortLevel"})
public class SortLevelController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SortLevelController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SortLevelController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sortField = request.getParameter("sortField");
        HttpSession session = request.getSession();
        Boolean descendingOrder = (Boolean) session.getAttribute("descendingOrder");

        if (descendingOrder == null) {
            descendingOrder = false; 
        }

        LevelDAO levelDAO = new LevelDAO();
        List<Level> levelList;

        if (sortField.equalsIgnoreCase("name")) {
            if (descendingOrder) {
                levelList = levelDAO.getAllLevelsSortedByNameAscending();
            } else {
                levelList = levelDAO.getAllLevelsSortedByNameDescending();
            }
        } else {
            if (descendingOrder) {
                levelList = levelDAO.getAllLevelsSortedByIdAscending();
            } else {
                levelList = levelDAO.getAllLevelsSortedByIdDescending();
            }
        }
        
        descendingOrder = !descendingOrder;

        // Phân trang
        int limitPage = 4;

        if (request.getParameter("cp") == null) {
            Pagination<Level> pagination = new Pagination<>(levelList, limitPage, 1);
            levelList = pagination.getItemsOnPage();
            session.setAttribute("page", pagination);
        } else {
            int currentPage = Integer.parseInt(request.getParameter("cp"));
            Pagination<Level> pagination = new Pagination<>(levelList, limitPage, currentPage);
            levelList = pagination.getItemsOnPage();
            session.setAttribute("page", pagination);
        }
        
        request.setAttribute("tList", levelList);
        request.setAttribute("pagging", "ManageLevel?" + "&");
        session.setAttribute("descendingOrder", descendingOrder);

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

package Controller;

import DAO.LevelDAO;
import DAO.TopicDAO;
import Model.Level;
import Model.Pagination;
import Model.Topic;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ManageLevelController", urlPatterns = {"/ManageLevel"})
public class ManageLevelController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageLevelController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageLevelController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (userSession == null || userSession.getRole().getRole_id() != 3) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            LevelDAO leveldao = new LevelDAO();
            List<Level> list = leveldao.getAllLevels();
            request.setAttribute("tList", list);
            String lv = request.getParameter("lv") == null ? "1" : request.getParameter("lv");

            request.setAttribute("selectedtlv", lv);

            // pagging cái list
            int limitPage = 4;
            if (request.getParameter("cp") == null) {
                Pagination Page = new Pagination(list, limitPage, 1);
                // list: list để phân trang, limitPage: giới hạn cho 1 trang có bao nhiêu bản record, 1: trang đầu tiên
                Pagination<Level> pagination = new Pagination<>(list, limitPage, 1);
                list = pagination.getItemsOnPage();
                session.setAttribute("page", Page);
                request.setAttribute("tList", pagination.getItemsOnPage());
            } else if (request.getParameter("cp") != null) {
                int cp = Integer.parseInt(request.getParameter("cp"));
                Pagination Page = new Pagination(list, limitPage, cp);
                // list: list để phân trang, limitPage: giới hạn cho 1 trang có bao nhiêu bản record, cp: currentPage
                Pagination<Level> pagination = new Pagination<>(list, limitPage, cp);
                list = pagination.getItemsOnPage();
                session.setAttribute("page", Page);
                request.setAttribute("tList", list);
            }
            // set URL
            request.setAttribute("pagging", "ManageLevel?" + "&");

            request.setAttribute("lv", leveldao.getAllLevels());

            request.getRequestDispatcher("manageLevel.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

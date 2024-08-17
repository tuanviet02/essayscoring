package Controller;

import DAO.TopicDAO;
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

@WebServlet(name = "ManagerTopicController", urlPatterns = {"/ManagerTopic"})
public class ManagerTopicController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (userSession == null || userSession.getRole().getRole_id() != 4) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            TopicDAO tDAO = new TopicDAO();
            String lv = request.getParameter("lv") == null ? "1" : request.getParameter("lv");
            String tId = request.getParameter("typeId");

            request.setAttribute("tId", tId);
            request.setAttribute("selectedtlv", lv);
            List<Topic> list = new ArrayList<>();
            if (tId != null) {
                list = tDAO.getTopicsByTypeAndLevel(Integer.parseInt(tId), Integer.parseInt(lv));
            } else {
                response.sendRedirect("error.jsp");
            }

            // pagging cái list
            int limitPage = 2;
            if (request.getParameter("cp") == null) {
                Pagination Page = new Pagination(list, limitPage, 1);
                // list: list để phân trang, limitPage: giới hạn cho 1 trang có bao nhiêu bản record, 1: trang đầu tiên
                Pagination<Topic> pagination = new Pagination<>(list, limitPage, 1);
                list = pagination.getItemsOnPage();
                session.setAttribute("page", Page);
                request.setAttribute("tList", pagination.getItemsOnPage());
            } else if (request.getParameter("cp") != null) {
                int cp = Integer.parseInt(request.getParameter("cp"));
                Pagination Page = new Pagination(list, limitPage, cp);
                // list: list để phân trang, limitPage: giới hạn cho 1 trang có bao nhiêu bản record, cp: currentPage
                Pagination<Topic> pagination = new Pagination<>(list, limitPage, cp);
                list = pagination.getItemsOnPage();
                session.setAttribute("page", Page);
                request.setAttribute("tList", list);
            }
            // set URL
            request.setAttribute("pagging", "ManagerTopic?typeId=" + tId + "&" + "lv=" + lv + "&");

            request.setAttribute("lv", tDAO.getAllLevel());

            request.getRequestDispatcher("manageTopic.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}

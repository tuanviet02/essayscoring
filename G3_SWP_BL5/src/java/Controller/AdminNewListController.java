/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.NewDAO;
import DAO.UserDAO;
import Model.New;
import Model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author nguye
 */
public class AdminNewListController extends HttpServlet {

    private static final NewDAO newDAO = new NewDAO();
    private static final UserDAO userDAO = new UserDAO();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (userSession == null || userSession.getRole().getRole_id() != 1) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            int PER_PAGE = 5;
            String title = request.getParameter("title") != null ? request.getParameter("title") : "";
            String userId = request.getParameter("userId") != null ? request.getParameter("userId") : "";
            String status = request.getParameter("status") != null ? request.getParameter("status") : "";
            int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
            ArrayList<User> users = userDAO.getAllAdmin();
            ArrayList<New> news = newDAO.getAllNew(title, page, status, userId);
            int count = newDAO.countGetAllNew(title, page, status, userId);
            int totalPage = count / PER_PAGE;
            if (totalPage % PER_PAGE != 0) {
                totalPage += 1;
            }
            request.setAttribute("users", users);
            request.setAttribute("news", news);
            request.setAttribute("title", title);
            request.setAttribute("status", status);
            request.setAttribute("userId", userId);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.getRequestDispatcher("/admin/newList.jsp").forward(request, response);
        }
    }

}

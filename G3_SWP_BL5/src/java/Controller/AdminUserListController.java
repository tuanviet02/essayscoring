/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.RoleDAO;
import DAO.UserDAO;
import Model.Role;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;

/**
 *
 * @author lam05
 */
public class AdminUserListController extends HttpServlet {

    private static final RoleDAO roleDAO = new RoleDAO();
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
        if (userSession == null || userSession.getRole().getRole_id()!= 1) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            int PER_PAGE = 5;
            String email = request.getParameter("email") != null ? request.getParameter("email") : "";
            String roleId = request.getParameter("roleId") != null ? request.getParameter("roleId") : "";
            String status = request.getParameter("status") != null ? request.getParameter("status") : "";
            int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
            ArrayList<Role> roles = roleDAO.getAll();
            ArrayList<User> users = userDAO.getAllUsers(email, page, status, roleId);
            int count = userDAO.countGetAllUsers(email, status, roleId);
            int totalPage = count / PER_PAGE;
            if (totalPage % PER_PAGE != 0) {
                totalPage += 1;
            }
            request.setAttribute("roles", roles);
            request.setAttribute("users", users);
            request.setAttribute("email", email);
            request.setAttribute("status", status);
            request.setAttribute("roleId", roleId);

            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.getRequestDispatcher("/admin/userList.jsp").forward(request, response);
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.RoleDAO;
import DAO.UserDAO;
import Model.Role;
import Model.User;
import Validate.Validate;
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
public class AdminUserUpdateController extends HttpServlet {

    private static final RoleDAO roleDAO = new RoleDAO();
    private static final UserDAO userDao = new UserDAO();
    private static final Validate validate = new Validate();

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

            int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 1;
            User user = userDao.findById(id);

            ArrayList<Role> roles = roleDAO.getAll();

            request.setAttribute("roles", roles);
            request.setAttribute("action", "./users-update");

            request.setAttribute("id", user.getUserID());
            request.setAttribute("status", user.isStatus());
            request.setAttribute("phone", user.getPhone());
            request.setAttribute("fullname", user.getFullName());
            request.setAttribute("password", user.getPassword());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("gender", user.getGender());
            request.setAttribute("roleId", user.getRole().getRole_id());

            request.getRequestDispatcher("/admin/userDetail.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (userSession == null || userSession.getRole().getRole_id() != 1) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            int roleId = request.getParameter("roleId") != null ? Integer.parseInt(request.getParameter("roleId")) : null;
            int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : null;
            String statusString = request.getParameter("status") != null ? request.getParameter("status") : "";
            boolean status = "1".equals(statusString);
            User user = new User();
            user.setRoleId(roleId);
            user.setUserID(id);
            user.setStatus(status);
            new UserDAO().updateStatusAndRole(user);
            response.sendRedirect("./users");
        }
    }
}

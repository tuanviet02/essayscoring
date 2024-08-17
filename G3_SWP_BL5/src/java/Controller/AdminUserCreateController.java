/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.RoleDAO;
import DAO.UserDAO;
import Helper.EncryptionUtil;
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
public class AdminUserCreateController extends HttpServlet {

    private static final RoleDAO roleDAO = new RoleDAO();
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
            ArrayList<Role> roles = roleDAO.getAll();
            request.setAttribute("roles", roles);
            request.setAttribute("action", "./users-create");
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
            String phone = request.getParameter("phone").trim();
            String fullname = request.getParameter("fullname");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            int roleId = request.getParameter("roleId") != null ? Integer.parseInt(request.getParameter("roleId")) : null;
            boolean gender = request.getParameter("gender") != null ? Boolean.parseBoolean(request.getParameter("gender")) : false;

            String errPhone = null;
            String errFullname = null;
            String errPassword = null;
            String errEmail = null;
            boolean error = false;

            // validate phone 
            if (validate.isRequire(phone)) {
                errPhone = "The phone number is required";
                error = true;
            } else if (!validate.isNumberPhone(phone)) {
                errPhone = "The phone number is in the wrong format";
                error = true;
            }
            // validate fullname 
            if (validate.isRequire(fullname)) {
                errFullname = "Fullname is required";
                error = true;
            }
            // validate password 
            if (validate.isRequire(password)) {
                errPassword = "Password is required";
                error = true;
            } else if (!validate.isPassword(password)) {
                errPassword = "Password is in the wrong format";
                error = true;
            }
            // validate email 
            if (validate.isRequire(email)) {
                errEmail = "Email is required";
                error = true;
            } else if (!validate.isEmail(email)) {
                errEmail = "Email is in the wrong format";
                error = true;
            } else if (validate.checkEmail(email)) {
                errEmail = "Email has been used";
                error = true;
            }

            if (error) {
                ArrayList<Role> roles = roleDAO.getAll();
                request.setAttribute("roles", roles);
                request.setAttribute("action", "./users-create");
                request.setAttribute("errPhone", errPhone);
                request.setAttribute("errFullname", errFullname);
                request.setAttribute("errPassword", errPassword);
                request.setAttribute("errEmail", errEmail);

                request.setAttribute("phone", phone);
                request.setAttribute("fullname", fullname);
                request.setAttribute("password", password);
                request.setAttribute("email", email);
                request.setAttribute("gender", gender);
                request.setAttribute("roleId", roleId);
                request.getRequestDispatcher("./userDetail.jsp").forward(request, response);
            } else {
                User user = new User();
                EncryptionUtil md5 = new EncryptionUtil();
                user.setEmail(email);
                user.setPassword(md5.getMd5(password));
                user.setFullName(fullname);
                user.setPhone(phone);
                user.setGender(gender);
                user.setRoleId(roleId);
                user.setStatus(true);
                new UserDAO().createUser(user);
                response.sendRedirect("./users");
            }
        }
    }

}

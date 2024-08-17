/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.NewDAO;
import DAO.UserDAO;
import Model.User;
import Validate.Validate;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
@WebServlet(name = "AdminNewsCreateController", urlPatterns = {"/admin/news-create"})
public class AdminNewsCreateController extends HttpServlet {

    private static final NewDAO newDAO = new NewDAO();
    private static final UserDAO userDAO = new UserDAO();
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
            ArrayList<User> users = userDAO.getAllAdmin();
            request.setAttribute("users", users);
            request.setAttribute("action", "./news-create");
            request.getRequestDispatcher("/admin/newDetail.jsp").forward(request, response);
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
            ArrayList<User> users = userDAO.getAllAdmin();
            String title = request.getParameter("title") != null ? request.getParameter("title").trim() : "";
            String content = request.getParameter("content") != null ? request.getParameter("content") : "";
            String userId = request.getParameter("userId") != null ? request.getParameter("userId") : "";
            String status = request.getParameter("status") != null ? request.getParameter("status") : "";
            String errTitle = null;
            String errContent = null;
            if (validate.isRequire(title)) {
                errTitle = "Title is required";
            }
            if (validate.isRequire(content)) {
                errContent = "Content is required";
            }

            if (errTitle != null || errContent != null) {
                request.setAttribute("title", title);
                request.setAttribute("content", content);
                request.setAttribute("userId", userId);
                request.setAttribute("status", status);
                request.setAttribute("errContent", errContent);
                request.setAttribute("errTitle", errTitle);
                request.setAttribute("users", users);
                request.setAttribute("action", "./news-create");
                request.getRequestDispatcher("./newDetail.jsp").forward(request, response);
            } else {
                newDAO.create(title, content, userId, status);
                response.sendRedirect("./news");
            }
        }
    }

}

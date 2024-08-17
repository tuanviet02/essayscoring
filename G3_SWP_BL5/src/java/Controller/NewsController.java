/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.NewDAO;
import Model.New;
import Model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class NewsController extends HttpServlet {

    private static final NewDAO newDAO = new NewDAO();

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
        if (userSession == null || userSession.getRole().getRole_id() != 4) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            String id = request.getParameter("id") != null ? request.getParameter("id") : "";
            ArrayList<New> news = newDAO.getAllHide();
            if (!"".equals(id)) {
                New newDetail = newDAO.findById(Integer.parseInt(id));
                System.out.println(newDetail.getCreated_date());
                request.setAttribute("newDetail", newDetail);
                request.setAttribute("id", id);

            }
            request.setAttribute("news", news);
            request.getRequestDispatcher("/news.jsp").forward(request, response);

        }
    }
}

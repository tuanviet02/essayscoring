/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CriteriaDAO;
import Model.Criteria;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;

/**
 *
 * @author AnhTH
 */
@WebServlet(name = "AddCriteriaController", urlPatterns = {"/addCriteria"})
public class AddCriteriaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddCriteriaController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCriteriaController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        if (userSession == null || userSession.getRole().getRole_id() != 3) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.getRequestDispatcher("addCriteria.jsp").forward(request, response);
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
        if (userSession == null || userSession.getRole().getRole_id() != 3) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.setCharacterEncoding("UTF-8");

            String criteriaName = request.getParameter("criteriaName");
            String description = request.getParameter("description");
            if (criteriaName == null || criteriaName.trim().isEmpty()) {
                request.setAttribute("messError", "Error: Criteria Name is required.");
                doGet(request, response);
                return;
            }
            if (description == null || description.trim().isEmpty()) {
                request.setAttribute("messError", "Error: Description is required.");
                doGet(request, response);
                return;
            }
            CriteriaDAO criteriaDAO = new CriteriaDAO();
            if (criteriaDAO.isCriteriaNameExists(criteriaName)) {
                request.setAttribute("messError", "Error: Duplicate criteria name.");
                doGet(request, response);
                return;
            }
            Criteria newCriteria = new Criteria();
            newCriteria.setCriteriaName(criteriaName);
            newCriteria.setDescription(description);

            try {
                criteriaDAO.addCriteria(newCriteria);
                request.setAttribute("messSuccess", "Success: Add criteria successfully.");
                doGet(request, response);
            } catch (Exception e) {
                request.setAttribute("messError", "messError: Add criteria fail.");
                doGet(request, response);
            }

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

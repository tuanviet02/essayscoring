/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CriteriaDAO;
import Model.CriteriaFeedback;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author AnhTH
 */
@WebServlet(name = "EditCriteriaFeedbackController", urlPatterns = {"/editCriteriaFeedback"})
public class EditCriteriaFeedbackController extends HttpServlet {

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
            out.println("<title>Servlet EditCriteriaFeedbackController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditCriteriaFeedbackController at " + request.getContextPath() + "</h1>");
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
            int evaluatorEssayId = Integer.parseInt(request.getParameter("evaluator_essay_id"));

            CriteriaDAO criteriaDAO = new CriteriaDAO();
            List<CriteriaFeedback> criteriaFeedbackList = criteriaDAO.getFeedbackCriteriabyEvaluatorEssay(evaluatorEssayId);
            if (criteriaFeedbackList == null || criteriaFeedbackList.isEmpty()) {
                request.setAttribute("mess", "Error:  There are no Criteria reviews yet. Please rate and try again");
                request.getRequestDispatcher("editcriteriaFeedback.jsp").forward(request, response);
                return;
            }

            request.setAttribute("criteriaFeedbackList", criteriaFeedbackList);

            request.getRequestDispatcher("editcriteriaFeedback.jsp").forward(request, response);
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
            int feedbackId = Integer.parseInt(request.getParameter("criteria_feedback_id"));
            String feedbackContent = request.getParameter("feedback_content");
            if (feedbackContent == null || feedbackContent.trim().isEmpty()) {
                request.setAttribute("messError", "Error: Feedback content is required.");
                doGet(request, response);
                return;
            }
            CriteriaDAO criteriaDAO = new CriteriaDAO();
            try {
                criteriaDAO.editFeedbackCriteria(feedbackId, feedbackContent);
                request.setAttribute("messSuccess", "Success: Send criteria successfully.");
                doGet(request, response);

            } catch (Exception e) {
                request.setAttribute("messError", "Error: Send criteria fail.");
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

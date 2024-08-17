/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CriteriaDAO;
import Model.Criteria;
import Model.CriteriaFeedback;
import Model.EvaluatorEssay;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author AnhTH
 */
@WebServlet(name = "TopicCriteriaController", urlPatterns = {"/topicCriteria"})
public class TopicCriteriaController extends HttpServlet {

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
            out.println("<title>Servlet TopicCriteriaController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TopicCriteriaController at " + request.getContextPath() + "</h1>");
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
        int writerEssayId = Integer.parseInt(request.getParameter("writerEssayId"));
        CriteriaDAO criteriaDAO = new CriteriaDAO();
        List<Criteria> criteriaList = criteriaDAO.getCriteriaByWriterEssayID(writerEssayId);
        if (criteriaList == null || criteriaList.isEmpty()) {
            request.setAttribute("mess", "Error: Not Found Criteria.");
            request.getRequestDispatcher("criteriaFeedback.jsp").forward(request, response);
            return;
        }
        request.setAttribute("criteriaList", criteriaList);

        request.getRequestDispatcher("criteriaFeedback.jsp").forward(request, response);
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
        int evaluatorEssayID = Integer.parseInt(request.getParameter("evaluator_essay_id"));
        int criteriaID = Integer.parseInt(request.getParameter("criteria_id"));
        String feedbackContent = request.getParameter("feedback_content");
        if (evaluatorEssayID == -1) {

                request.setAttribute("messError", "Error: You must grading before. Plase grading to continue.");
                doGet(request, response);
                return;
        }
        if (feedbackContent == null || feedbackContent.trim().isEmpty()) {
            request.setAttribute("messError", "Error: Feedback content is required.");
            doGet(request, response);
            return;
        }
        CriteriaDAO criteriaDAO = new CriteriaDAO();
        if (!criteriaDAO.checkUniqueFeedback(evaluatorEssayID, criteriaID)) {
            request.setAttribute("messError", "Error: You was feedback.");
            doGet(request, response);
            return;
        }
        EvaluatorEssay evaluatorEssay = new EvaluatorEssay();
        evaluatorEssay.setEvaluatorEssayID(evaluatorEssayID);
        Criteria criteria = new Criteria();
        criteria.setCriteriaID(criteriaID);
        CriteriaFeedback criteriaFeedback = new CriteriaFeedback();
        criteriaFeedback.setEvaluatorEssay(evaluatorEssay);
        criteriaFeedback.setCriteria(criteria);
        criteriaFeedback.setFeedbackContent(feedbackContent);
        try {
            criteriaDAO.createFeedbackCriteria(criteriaFeedback);
            request.setAttribute("messSuccess", "Success: Send criteria successfully.");
            doGet(request, response);

        } catch (Exception e) {
            request.setAttribute("messError", "Error: Send criteria fail.");
            doGet(request, response);
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

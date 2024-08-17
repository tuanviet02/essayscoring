/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.TopicDAO;
import DAO.WriterEssayDAO;
import Model.Topic;
import Model.User;
import Model.WriterEssay;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author AnhTH
 */
@WebServlet(name = "CreateWriterEssayController", urlPatterns = {"/createWriterEssay"})
public class CreateWriterEssayController extends HttpServlet {

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

        String idString = request.getParameter("essay_id");

        if (idString != null && idString.matches("\\d+")) {
            int id = Integer.parseInt(idString);

            TopicDAO topicDAO = new TopicDAO();
            Topic topic = topicDAO.getTopicById(id);
            if (topic == null) {

                request.setAttribute("messError", "Error: Topic not exist.");
                request.getRequestDispatcher("createWriterEssay.jsp").forward(request, response);
                return;
            }

            request.setAttribute("topic", topic);

            request.getRequestDispatcher("/createWriterEssay.jsp").forward(request, response);
        } else {

            request.setAttribute("messError", "Error: Topic not exist.");
            request.getRequestDispatcher("createWriterEssay.jsp").forward(request, response);
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
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {

                response.sendRedirect("login");
                return;
            }

            int writerId = (int) session.getAttribute("userID");
            int essayId = Integer.parseInt(request.getParameter("essay_id"));
            String contentEssay = request.getParameter("content_essay");
            int limit = 3;
            String status = "Pending";
            if (contentEssay == null || contentEssay.trim().isEmpty()) {
                request.setAttribute("messError", "Error: Feedback content is required.");
                doGet(request, response);
                return;
            }
            WriterEssayDAO writerEssayDAO = new WriterEssayDAO();
            if (writerEssayDAO.isPendingStatus(writerId, essayId)) {
                request.setAttribute("messError", "Error: Your essay currently grading of evaluator.");
                doGet(request, response);
                return;
            }

            if (!writerEssayDAO.isDuplicateWithinLimit(writerId, essayId)) {
                request.setAttribute("messError", "Error: Duplicate essay entry within limit.");
                doGet(request, response);
                return;
            }

            User writer = new User();
            writer.setUserID(writerId);

            Topic essay = new Topic();
            essay.setTopicId(essayId);

            WriterEssay writerEssay = new WriterEssay();
            writerEssay.setWriter(writer);
            writerEssay.setEssay(essay);
            writerEssay.setContentEssay(contentEssay);
            writerEssay.setLimit(limit);
            writerEssay.setStatus(status);

            boolean created = writerEssayDAO.createWriterEssay(writerEssay);

            if (created) {
                request.setAttribute("messSuccess", "Success: Essay submitted successfully.");
                doGet(request, response);
            } else {
                request.setAttribute("messError", "Error: Failed to submit essay.");
                doGet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("messError", "Error: Exception occurred.");
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

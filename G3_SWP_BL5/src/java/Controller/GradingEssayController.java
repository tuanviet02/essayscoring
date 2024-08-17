/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.EvaluatorEssayDAO;
import DAO.WriterEssayDAO;
import Model.EvaluatorEssay;
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
@WebServlet(name = "GradingEssayController", urlPatterns = {"/grading"})
public class GradingEssayController extends HttpServlet {

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
            out.println("<title>Servlet WriterEssayDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WriterEssayDetailController at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (userSession == null || userSession.getRole().getRole_id() != 3) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            String idString = request.getParameter("writerEssayId");

            if (idString != null) {
                int id = Integer.parseInt(idString);

                WriterEssayDAO writerEssayDAO = new WriterEssayDAO();
                WriterEssay writerEssay = writerEssayDAO.getWriterEssayById(id);
                if (writerEssay == null) {

                    request.setAttribute("messError", "Error: Essay not exist.");
                    request.getRequestDispatcher("gradingEssay.jsp").forward(request, response);
                    return;
                }
                EvaluatorEssayDAO evaluatorEssayDAO = new EvaluatorEssayDAO();
                int evaluatorEssayID = evaluatorEssayDAO.getEvaluatorEssayIDByWriterEssayID(id);
                request.setAttribute("evaluatorEssayID", evaluatorEssayID);
                request.setAttribute("writerEssay", writerEssay);

                request.getRequestDispatcher("gradingEssay.jsp").forward(request, response);
            } else {

                request.setAttribute("messError", "Error: Essay not exist.");
                request.getRequestDispatcher("gradingEssay.jsp").forward(request, response);
            }
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
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            int writerEssayId = Integer.parseInt(request.getParameter("writerEssayId"));
            String feedbackContent = request.getParameter("feedbackContent");
            double score = Double.parseDouble(request.getParameter("score"));
            User user = (User) session.getAttribute("user");
            if (user == null) {

                response.sendRedirect("login");
                return;
            }
            int evaluatorId = (int) session.getAttribute("userID");
            if (score < 0 || score > 10) {
                request.setAttribute("messError", "Error: Score must be between 0 and 10.");
                doGet(request, response);
                return;
            }
            if (feedbackContent == null || feedbackContent.trim().isEmpty()) {
                request.setAttribute("messError", "Error: Feedback content is required.");
                doGet(request, response);
                return;
            }

            EvaluatorEssayDAO evaluatorEssayDAO = new EvaluatorEssayDAO();
            if (evaluatorEssayDAO.isWriterEssayAlreadyEvaluated(writerEssayId)) {
                request.setAttribute("messError", "Error: You was already been grading.");
                doGet(request, response);
                return;
            }

            EvaluatorEssay evaluatorEssay = new EvaluatorEssay();
            WriterEssay writerEssay = new WriterEssay();
            User evaluator = new User();

            writerEssay.setWriterEsssayId(writerEssayId);
            evaluatorEssay.setWriterEssay(writerEssay);
            evaluatorEssay.setFeedbackContent(feedbackContent);
            evaluatorEssay.setScore(score);
            evaluator.setUserID(evaluatorId);
            evaluatorEssay.setEvaluator(evaluator);

            boolean isSuccess = evaluatorEssayDAO.createEvaluatorEssay(evaluatorEssay);

            if (isSuccess) {
                WriterEssayDAO writerEssayDAO = new WriterEssayDAO();
                writerEssayDAO.updateStatusWritterEssay(writerEssayId);
                request.setAttribute("messSuccess", "Success: Essay submitted successfully.");
                doGet(request, response);
            } else {
                request.setAttribute("messError", "Error: Failed to submit essay.");
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

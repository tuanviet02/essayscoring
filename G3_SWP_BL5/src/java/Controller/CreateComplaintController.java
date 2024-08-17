/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ComplaintDAO;
import DAO.EvaluatorEssayDAO;
import Model.Complaint;
import Model.EvaluatorEssay;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Sang
 */
public class CreateComplaintController extends HttpServlet {

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
            out.println("<title>Servlet CreateComplaintController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateComplaintController at " + request.getContextPath() + "</h1>");
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
        EvaluatorEssayDAO evaluatorEssayDAO = new EvaluatorEssayDAO();
        String raw_index = request.getParameter("index");
        if (raw_index == null) {
            raw_index = "1";
        }
        HttpSession session = request.getSession();
        String rawEID = request.getParameter("eID");
        if (rawEID != null) {
            int eID = Integer.parseInt(rawEID);
            EvaluatorEssay evaluatorEssay = evaluatorEssayDAO.getEvaluatorEssayByID(eID);
            session.setAttribute("evaluatorEssay", evaluatorEssay);
        } else {
            int index = Integer.parseInt(raw_index);
//            int writerID = (int) session.getAttribute("userID");
            int writerID = 25;
            int totalEvaluatorEssay = evaluatorEssayDAO.countAllEvaluatorEssayByWriterID(writerID);
            int page = totalEvaluatorEssay % 5 == 0 ? totalEvaluatorEssay / 5 : totalEvaluatorEssay / 5 + 1;
            List<EvaluatorEssay> gradedEssays = evaluatorEssayDAO.getListEvaluatorEssayPagingByWriterID(writerID, index);
            request.setAttribute("gradedEssays", gradedEssays);
            request.setAttribute("index", index);
            request.setAttribute("page", page);
            session.setAttribute("evaluatorEssay", null);
        }
        request.getRequestDispatcher("createComplaint.jsp").forward(request, response);
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
        ComplaintDAO complaintDAO = new ComplaintDAO();
        HttpSession session = request.getSession();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        EvaluatorEssay evaluatorEssay = (EvaluatorEssay) session.getAttribute("evaluatorEssay");
        if (evaluatorEssay == null) {
            request.setAttribute("failedMessage", "No evaluator essay chosen!");
        } else {
            Complaint complaint = new Complaint();
            complaint.setComplaintTitle(title);
            complaint.setContent(content);
            complaint.setStatus("pending");
            complaint.setEvaluatorEssay(evaluatorEssay);
            boolean isCreated = complaintDAO.insertComplaint(complaint);
            if (isCreated) {
                request.setAttribute("successfulMessage", "Complaint created successfully!");
            } else {
                request.setAttribute("failedMessage", "Create complaint failed!");
            }
            session.removeAttribute("evaluatorEssay");
        }
        EvaluatorEssayDAO evaluatorEssayDAO = new EvaluatorEssayDAO();
        String raw_index = request.getParameter("index");
        if (raw_index == null) {
            raw_index = "1";
        }
        int index = Integer.parseInt(raw_index);
//            int writerID = (int) session.getAttribute("userID");
        int writerID = 25;
        int totalEvaluatorEssay = evaluatorEssayDAO.countAllEvaluatorEssayByWriterID(writerID);
        int page = totalEvaluatorEssay % 5 == 0 ? totalEvaluatorEssay / 5 : totalEvaluatorEssay / 5 + 1;
        List<EvaluatorEssay> gradedEssays = evaluatorEssayDAO.getListEvaluatorEssayPagingByWriterID(writerID, index);
        request.setAttribute("gradedEssays", gradedEssays);
        request.setAttribute("index", index);
        request.setAttribute("page", page);
        request.getRequestDispatcher("createComplaint.jsp").forward(request, response);
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

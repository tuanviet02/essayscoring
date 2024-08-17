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

/**
 *
 * @author Sang
 */
public class ComplaintDetail extends HttpServlet {

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
            out.println("<title>Servlet ComplaintDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComplaintDetail at " + request.getContextPath() + "</h1>");
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
        String complaintIDString = request.getParameter("complaintID");
        if (complaintIDString == null) {
            request.setAttribute("failedMessage", "No complaint chosen!");
        } else {
            int complaintID = Integer.parseInt(complaintIDString);
            ComplaintDAO complaintDAO = new ComplaintDAO();
            Complaint complaint = complaintDAO.getComplaintByID(complaintID);
            if (complaint == null) {
                request.setAttribute("failedMessage", "No complaint found!");
            }
            request.setAttribute("complaint", complaint);
            EvaluatorEssayDAO evaluatorEssayDAO = new EvaluatorEssayDAO();
            EvaluatorEssay evaluatorEssay = evaluatorEssayDAO.getEvaluatorEssayByID(complaint.getEvaluatorEssay().getEvaluatorEssayID());
            complaint.setEvaluatorEssay(evaluatorEssay);
            request.getRequestDispatcher("complaintDetail.jsp").forward(request, response);
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
        processRequest(request, response);
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

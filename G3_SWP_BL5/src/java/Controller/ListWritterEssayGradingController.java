/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.WriterEssayDAO;
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
import java.util.ArrayList;

/**
 *
 * @author AnhTH
 */
@WebServlet(name = "ListWritterEssayGradingController", urlPatterns = {"/listWritterEssayGrading"})
public class ListWritterEssayGradingController extends HttpServlet {

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
            out.println("<title>Servlet ListWritterEssayGradingController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListWritterEssayGradingController at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (userSession == null || userSession.getRole().getRole_id() != 3) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            int pageNo = 1;
            if (request.getParameter("pageNo") != null) {
                pageNo = Integer.parseInt(request.getParameter("pageNo"));
            }

            WriterEssayDAO writerEssayDAO = new WriterEssayDAO();

            int pageSize = 5;

            ArrayList<WriterEssay> writerEssays = writerEssayDAO.getAllWriterEssay(pageNo, pageSize);

            int totalEssays = writerEssayDAO.getTotalEssay();

            int totalPages = (int) Math.ceil((double) totalEssays / pageSize);
            request.setAttribute("pageNo", pageNo);
            request.setAttribute("writerEssays", writerEssays);
            request.setAttribute("totalPages", totalPages);

            request.getRequestDispatcher("listWritterEssayGrading.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (userSession == null || userSession.getRole().getRole_id() != 3) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            String keyword = request.getParameter("keyword");
            String status = request.getParameter("status");

            if ((keyword == null || keyword.isEmpty()) && (status == null || status.isEmpty())) {

                doGet(request, response);
                return;
            }

            WriterEssayDAO writerEssayDAO = new WriterEssayDAO();

            ArrayList<WriterEssay> writerEssays;

            if (status == null || status.isEmpty()) {
                writerEssays = writerEssayDAO.searchWriterEssay(keyword, "done");
                writerEssays.addAll(writerEssayDAO.searchWriterEssay(keyword, "pending"));
            } else {

                writerEssays = writerEssayDAO.searchWriterEssay(keyword, status);
            }

            request.setAttribute("writerEssays", writerEssays);

            request.getRequestDispatcher("listWritterEssayGrading.jsp").forward(request, response);
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

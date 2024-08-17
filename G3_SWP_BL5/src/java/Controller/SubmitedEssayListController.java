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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class SubmitedEssayListController extends HttpServlet {

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
            out.println("<title>Servlet SubmitedEssayListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubmitedEssayListController at " + request.getContextPath() + "</h1>");
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
        if (userSession == null || userSession.getRole().getRole_id() != 4) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            int userId = (Integer) session.getAttribute("userID");
            WriterEssayDAO writerEssayDAO = new WriterEssayDAO();
            TopicDAO topicDAO = new TopicDAO();
            List<WriterEssay> listTopic = new ArrayList<>();
            String raw_index = request.getParameter("index");
            if (raw_index == null) {
                raw_index = "1";
            }
            int total = writerEssayDAO.getTotalEssay(userId);
            int page = total / 5;
            if (total % 5 != 0) {
                page += 1;
            }
            int index = Integer.parseInt(raw_index);
            if (userId != 0) {
                listTopic = writerEssayDAO.getWriterEssay(userId, index);
            }

            request.setAttribute("listTopic", listTopic);
            request.setAttribute("check", "list");
            request.setAttribute("index", index);
            request.setAttribute("page", page);
            request.getRequestDispatcher("submitedessay.jsp").forward(request, response);
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

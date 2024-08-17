/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.EssayTypeDAO;
import DAO.LevelDAO;
import DAO.TopicDAO;
import Model.EssayType;
import Model.Level;
import Model.Topic;
import Model.User;
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
 * @author admin
 */
public class ApprovalTopicListController extends HttpServlet {

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
            out.println("<title>Servlet ApprovalTopicListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApprovalTopicListController at " + request.getContextPath() + "</h1>");
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
        if (userSession == null || userSession.getRole().getRole_id() != 2) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            TopicDAO topicDAO = new TopicDAO();
            LevelDAO levelDAO = new LevelDAO();
            EssayTypeDAO typeDAO = new EssayTypeDAO();
            String raw_index = request.getParameter("index");
            if (raw_index == null) {
                raw_index = "1";
            }
            int total = topicDAO.getTotalTopic();
            int page = total / 5;
            if (total % 5 != 0) {
                page += 1;
            }
            int index = Integer.parseInt(raw_index);

            List<Topic> topics = topicDAO.listTopicPaging(index);
            List<Level> levels = levelDAO.getAllLevel();
            List<EssayType> types = typeDAO.getAllType();

            request.setAttribute("topics", topics);
            request.setAttribute("listLevel", levels);
            request.setAttribute("listType", types);
            request.setAttribute("check", "list");
            request.setAttribute("index", index);
            request.setAttribute("page", page);
            request.getRequestDispatcher("approvaltopiclist.jsp").forward(request, response);
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
        if (userSession == null || userSession.getRole().getRole_id() != 2) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            processRequest(request, response);
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CriteriaDAO;
import DAO.EssayTypeDAO;
import DAO.LevelDAO;
import DAO.TopicDAO;
import Model.Criteria;
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
public class ApproveTopicUpdateStatusController extends HttpServlet {

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
            int id = Integer.parseInt(request.getParameter("id"));
            TopicDAO topicDAO = new TopicDAO();
            Topic topic = topicDAO.getTopicById(id);
            LevelDAO levelDAO = new LevelDAO();
            EssayTypeDAO typeDAO = new EssayTypeDAO();
            CriteriaDAO criteriaDAO = new CriteriaDAO();

            List<Level> listLevel = levelDAO.getAllLevel();
            List<EssayType> listType = typeDAO.getAllType();
            List<Criteria> listCriteria = criteriaDAO.getAllCritaria();

            request.setAttribute("topic", topic);
            request.setAttribute("listLevel", listLevel);
            request.setAttribute("listType", listType);
            request.setAttribute("listCriteria", listCriteria);
            request.getRequestDispatcher("approvetopic.jsp").forward(request, response);
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
            int topicId = Integer.parseInt(request.getParameter("id"));
            String status = request.getParameter("action");
            TopicDAO topicDAO = new TopicDAO();
            topicDAO.updateStatusTopic(topicId, status);
            response.sendRedirect("approvaltopiclist");
            //response.sendRedirect("approvetopic?id="+topicId);
        }
    }

}

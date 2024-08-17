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
import Validate.Validate;
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
public class EditTopicController extends HttpServlet {

    private static final Validate validate = new Validate();

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
            out.println("<title>Servlet EditEssayController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditEssayController at " + request.getContextPath() + "</h1>");
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
            request.getRequestDispatcher("edittopic.jsp").forward(request, response);
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
            request.setCharacterEncoding("UTF-8");
            int userId = (Integer) session.getAttribute("userID");
            //get param from jsp
            int topicId = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title").trim();
            int level = Integer.parseInt(request.getParameter("level"));
            int type = Integer.parseInt(request.getParameter("type"));
            String description = request.getParameter("description").trim();
            String[] cId = request.getParameterValues("criteria");      //get array from checkbox criteria
            String errTitle = null;

            if (validate.isRequire(title)) {
                errTitle = "Title is required";

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

                request.setAttribute("error", errTitle);
                request.getRequestDispatcher("edittopic.jsp").forward(request, response);
            }
            ArrayList<Criteria> list = new ArrayList<>();
            if (cId != null) {          //if user check then parse to int
                int[] criteriaId = new int[cId.length];
                for (int i = 0; i < cId.length; i++) {
                    criteriaId[i] = Integer.parseInt(cId[i]);      //parse criteria id to int
                    Criteria criteria = new Criteria(criteriaId[i], "", "");
                    list.add(criteria);
                }
            }

            Topic topic = new Topic();
            topic.setTopicId(topicId);
            topic.setTitle(title);
            topic.setDescription(description);

            User u = new User();  //set user id for essay
            u.setUserID(userId);
            topic.setUser(u);
            Level l = new Level(); //set level id for essay
            l.setLevelId(level);
            topic.setLevel(l);
            EssayType e = new EssayType(); //set type for essay
            e.setTypeId(type);
            topic.setType(e);

            topic.setCriteriaList(list);        //set list criteria
            TopicDAO topicDAO = new TopicDAO();
            topicDAO.updateTopic(topic);
            response.sendRedirect("topiclist");
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

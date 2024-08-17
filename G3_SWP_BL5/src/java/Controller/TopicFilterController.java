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
public class TopicFilterController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EssayFilterController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EssayFilterController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (Integer)session.getAttribute("userID");
        
        TopicDAO topicDAO = new TopicDAO();
        LevelDAO levelDAO = new LevelDAO();
        EssayTypeDAO typeDAO = new EssayTypeDAO();
        String level = request.getParameter("level");
        String type = request.getParameter("type");
        
        String raw_index = request.getParameter("index");
        if(raw_index==null){
            raw_index="1";
        }
        int total = topicDAO.getTotalTopicByFilter(level,type,userId);
        int page = total/5;
        if(total%5!=0){
            page+=1;
        }
        int index = Integer.parseInt(raw_index);
        int start = (index-1)*5;            //index start of a page
        int end = Math.min((index*5), total);       //index end of a page
        List<Topic> listTopic = topicDAO.filterTopic(level,type,userId);
        List<Level> listLevel = levelDAO.getAllLevel();
        List<EssayType> listType = typeDAO.getAllType();
        
        request.setAttribute("listTopic", listTopic.subList(start,end));        //return a list of start index to end index
        request.setAttribute("level", level);
        request.setAttribute("type", type);
        request.setAttribute("listLevel", listLevel);
        request.setAttribute("listType", listType);
        request.setAttribute("check", "filter");
        request.setAttribute("index", index);
        request.setAttribute("page", page);
        request.getRequestDispatcher("topiclist.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

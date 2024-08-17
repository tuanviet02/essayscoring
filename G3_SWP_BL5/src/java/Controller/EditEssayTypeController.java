/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.EssayTypeDAO;
import Model.EssayType;
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
public class EditEssayTypeController extends HttpServlet {

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
            out.println("<title>Servlet EditEssayTypeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditEssayTypeController at " + request.getContextPath() + "</h1>");
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
            EssayTypeDAO essayTypeDAO = new EssayTypeDAO();
            EssayType essayType = essayTypeDAO.getTypeById(id);
            request.setAttribute("essayType", essayType);
            request.getRequestDispatcher("editessaytype.jsp").forward(request, response);
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
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            EssayTypeDAO essayTypeDAO = new EssayTypeDAO();
            List<EssayType> listEssayType = essayTypeDAO.getAllType();

            if (name == null || name.equals("")) {
                request.setAttribute("error", "Name can not empty");
                //response.sendRedirect("editessaytype?id=" + id);
                request.getRequestDispatcher("editessaytype.jsp").forward(request, response);
            } else {
                boolean checkTypeExist = false;     //check type name
                for (EssayType e : listEssayType) {
                    if (e.getTypeName().equalsIgnoreCase(name)) {
                        checkTypeExist = true;
                        break;
                    }
                }
                if (checkTypeExist) {
                    request.setAttribute("error", "Essay Type is already exist");
                    //response.sendRedirect("editessaytype?id=" + id);
                    request.getRequestDispatcher("editessaytype.jsp").forward(request, response);
                }
                if (checkTypeExist == false) {
                    EssayType essayType = new EssayType();
                    essayType.setTypeId(id);
                    essayType.setTypeName(name);
                    essayTypeDAO.updateType(essayType);
                    response.sendRedirect("essaytypelist");
                }
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

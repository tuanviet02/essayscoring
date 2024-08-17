/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import Helper.EncryptionUtil;
import Model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author THTP
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        User user = (User) session.getAttribute("user");
        if (user == null) {

            if (request.getParameter("action") != null) {
                request.getSession().invalidate();
                response.sendRedirect("index.jsp");
            } else {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            int type = user.getRole().getRole_id();
            switch (user.getRoleId()) {
                case 1:
                    response.sendRedirect("admin/users");
                    break;
                case 2:
                    response.sendRedirect("approvaltopiclist");
                    break;
                case 3:
                    response.sendRedirect("listWritterEssayGrading");
                    break;
                case 4:
                    response.sendRedirect("Homepage");
                    break;
                default:
                    response.sendRedirect("");
                    break;
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
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        EncryptionUtil md5 = new EncryptionUtil();
        System.out.println(username);
        System.out.println(md5.getMd5(password));

        User user = userDAO.login(username, md5.getMd5(password));
        if (user != null) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(5 * 60);
            if (!user.isStatus()) {
                request.setAttribute("errmsg", "User is blocked pls contact with admin to open this account!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                session.setAttribute("userID", user.getUserID());
                session.setAttribute("user", user);
                switch (user.getRoleId()) {
                    case 1:
                        response.sendRedirect("admin/users");
                        break;
                    case 2:
                        response.sendRedirect("approvaltopiclist");
                        break;
                    case 3:
                        response.sendRedirect("listWritterEssayGrading");
                        break;
                    case 4:
                        response.sendRedirect("Homepage");
                        break;
                    default:
                        response.sendRedirect("");
                        break;
                }
            }
        } else {
            request.setAttribute("errmsg", "Username or password is incorrect!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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

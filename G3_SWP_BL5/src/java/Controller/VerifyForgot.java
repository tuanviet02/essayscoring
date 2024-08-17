/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.UserDAO;
import Helper.EncryptionUtil;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author VIETHUNG
 */
@WebServlet(name="VerifyForgot", urlPatterns={"/verifyForgot"})
public class VerifyForgot extends HttpServlet {
   
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
            out.println("<title>Servlet VerifyForgot</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyForgot at " + request.getContextPath () + "</h1>");
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        String emailToken = req.getParameter("emailToken");
        EncryptionUtil md5 = new EncryptionUtil();
         RequestDispatcher dispatcher = null;
        UserDAO uDAO = new UserDAO();
        try {
            String email = md5.decryptAES(emailToken);

            if (uDAO.checkEmail(email)) {
                req.setAttribute("email", req.getParameter("email"));
//            request.setAttribute("status", "success");
            dispatcher = req.getRequestDispatcher("newPassword.jsp");
            dispatcher.forward(req, resp);
            } else {
                req.getRequestDispatcher("emailVerify.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            PrintWriter out = resp.getWriter();

             req.setAttribute("message", "wrong otp");

            dispatcher = req.getRequestDispatcher("EnterOtp.jsp");
            dispatcher.forward(req, resp);
        }

    
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("otp");
        HttpSession mySession = request.getSession();
        String otp = (String) mySession.getAttribute("otp");
        RequestDispatcher dispatcher = null;

        if (value.equals(otp)) {
            request.setAttribute("email", request.getParameter("email"));
//            request.setAttribute("status", "success");
            dispatcher = request.getRequestDispatcher("newPassword.jsp");
            dispatcher.forward(request, response);

        } else {
            request.setAttribute("message", "wrong otp");

            dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
            dispatcher.forward(request, response);
        }
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

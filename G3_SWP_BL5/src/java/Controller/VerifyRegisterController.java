/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import Helper.EncryptionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author THTP
 */
@WebServlet(name = "verifyUser", urlPatterns = {"/verifyUser"})
public class VerifyRegisterController extends HttpServlet {

   @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String otp = req.getParameter("otp");
  
    UserDAO uDAO = new UserDAO();
    try {
        if (uDAO.verifyOTP(otp)) {
            uDAO.updateUserVerified(otp);
            
            req.setAttribute("message", "Email verify successful!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "OTP not validate!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    } catch (Exception e) {
        PrintWriter out = resp.getWriter();
        String message = "Wrong OTP. Please try late!";
        out.println(message);
    }
}
}

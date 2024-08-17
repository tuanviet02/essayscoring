package Controller;

import DAO.UserDAO;
import Helper.EncryptionUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ValidateOtp")
public class ValidateOtp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String OTP = request.getParameter("OTP");
        HttpSession mySession = request.getSession();
        String sessionOTP = (String) mySession.getAttribute("otp");
        RequestDispatcher dispatcher;

        if (OTP != null && OTP.equals(sessionOTP)) {
            request.setAttribute("message", "Verify successful. Please enter password news!");
           request.getRequestDispatcher("newPassword.jsp").forward(request, response);
        } else {
               PrintWriter out = response.getWriter();
        String message = "Wrong OTP. Please try late!";
        out.println(message);
        }
    }
}
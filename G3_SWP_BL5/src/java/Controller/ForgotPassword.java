/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.UserDAO;
import Model.User;
import Helper.EncryptionUtil;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {
  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        RequestDispatcher dispatcher = null;
        HttpSession mySession = request.getSession();

        if (email != null && !email.isEmpty()) {
            UserDAO userDAO = new UserDAO();
            User existingUser = userDAO.findByEmail(email);

            if (existingUser != null) {
                int otpvalueLength = 6;
                Random rand = new Random();
                String string = "0123456789";
                String randomOtp = "";

                for (int i = 0; i < otpvalueLength; i++) {
                    char c = string.charAt(rand.nextInt(string.length()));
                    randomOtp = randomOtp + c;
                }

                String to = email;
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("bnvqm1721@gmail.com", "tgqwyawkaytmqvka");
                    }
                });

                try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(email));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    message.setSubject("Request to reset password ");
                    message.setText("Hi, for security, please verify your account with the OPT below. "
                            + "Click the link to enter otp: "
                            +"http://localhost:9999/G3_SWP_BL5/ValidateOtp?OTP=" + randomOtp);
                    Transport.send(message);
                    Logger.getLogger(ForgotPassword.class.getName()).log(Level.INFO, "Email sent successfully");
                } catch (MessagingException e) {
                   System.out.println(e);
                    request.setAttribute("errmsg", "Failed to send OTP. Please try again.");
                    dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
                    dispatcher.forward(request, response);
                    return;
                }

                dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
                request.setAttribute("message", "OTP is sent to your email.Please open the link on the same browser page");
                mySession.setAttribute("otp", randomOtp);
                mySession.setAttribute("email", email);
                dispatcher.forward(request, response);
            } else {
                
                request.setAttribute("errmsg", "Email does not exist in the database. Please provide a registered email.");
                dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            
            request.setAttribute("errmsg", "Invalid email format. Please provide a valid email.");
            dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
            dispatcher.forward(request, response);
        }
    }

}

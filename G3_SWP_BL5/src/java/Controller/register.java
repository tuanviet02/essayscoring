/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import Helper.EncryptionUtil;
import Model.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author THTP
 */
@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String fullName = request.getParameter("fullName");
    String phone = request.getParameter("phone");
    String confirmPassword = request.getParameter("confirmPassword");
    boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
    if (fullName == null || fullName.trim().isEmpty()) {
            request.setAttribute("errmsg", "Error: FullName is required.");
            doGet(request, response);
            return;
        }
    User user = new User();
    EncryptionUtil md5 = new EncryptionUtil();

    user.setEmail(email);
    user.setPassword(md5.getMd5(password));
    user.setFullName(fullName);
    user.setPhone(phone);
    user.setGender(gender);

    UserDAO userDAO = new UserDAO();
    if (password.equals(confirmPassword)) {
        if (!userDAO.checkEmail(email)) {
            int otpvalueLength = 6;
            Random rand = new Random();
            String string = "0123456789";
            String randomOtp = "";
            HttpSession mySession = request.getSession();
            for (int i = 0; i < otpvalueLength; i++) {
                char c = string.charAt(rand.nextInt(string.length()));
                randomOtp = randomOtp + c;
            }

            if (userDAO.createUser(user, randomOtp)) {
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
                    message.setFrom(new InternetAddress("bnvqm1721@gmail.com"));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    message.setSubject("Please verify your account <3");
                    message.setText("Hi, for security, please verify your account with the link below. "
                            + "Your OTP is: http://localhost:9999/G3_SWP_BL5/verifyUser?otp=" + randomOtp
                    );
                    Transport.send(message);
                    request.setAttribute("message", "Please check your email to confirm");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }           
                mySession.setAttribute("otp", randomOtp);
                mySession.setAttribute("email", email);
            } else {
                request.setAttribute("errmsg", "Something went wrong!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errmsg", "Email already exists!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    } else {
        request.setAttribute("errmsg", "Confirm password are not same with password already exists!");
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

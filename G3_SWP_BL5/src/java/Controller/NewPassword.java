package Controller;

import DAO.UserDAO;
import Helper.EncryptionUtil;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/newPassword")
public class NewPassword extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String newPassword = request.getParameter("password");
        String confPassword = request.getParameter("newpassword");

        RequestDispatcher dispatcher = null;
        if (newPassword != null && confPassword != null && newPassword.equals(confPassword)
                && (newPassword.length() >= 6 && newPassword.length() <= 32)
                && (confPassword.length() >= 6 && confPassword.length() <= 32)) {
            try {
                UserDAO udbc = new UserDAO();
                EncryptionUtil bCrypt = new EncryptionUtil();
                
                udbc.updateUserPassword(email, bCrypt.getMd5(newPassword));
                request.setAttribute("status", "Reset Success");
                dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (newPassword == null || newPassword.isEmpty() && confPassword == null || confPassword.isEmpty()) {
            request.setAttribute("errorMessage", "Please enter your password here");
            request.getRequestDispatcher("newPassword.jsp").forward(request, response);

        } else {
            request.setAttribute("status", "Reset Failed");
            request.getRequestDispatcher("newPassword.jsp").forward(request, response);

        }
    }

}

package Controller;

import DAO.UserDAO;
import Helper.EncryptionUtil;
import Model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/changepassword")

public class ChangePassword extends HttpServlet {

    private final EncryptionUtil bCrypt = new EncryptionUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String oldPassword = request.getParameter("oldpass");
        String newPassword = request.getParameter("newpass");
        String confPassword = request.getParameter("renewpass");

        if (oldPassword.isEmpty() || newPassword.isEmpty() || confPassword.isEmpty()) {
            request.setAttribute("errorMessage", "Please fill in all password fields");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        } else if (newPassword.length() < 5) {
            request.setAttribute("errorMessage", "New password must be at least 8 characters long");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        } else if (user != null) {
            if (newPassword.equals(confPassword)) {
                if (user.getPassword().equals(bCrypt.getMd5(oldPassword))) {
                    user.setPassword(bCrypt.getMd5(newPassword));
                    UserDAO userDAO = new UserDAO();
                    try {
                        userDAO.updatePasswordUser(user.getEmail(), bCrypt.getMd5(newPassword));
                        request.setAttribute("errorMessage", "Password was changed successfully");
                        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                    } catch (Exception ex) {
                        request.setAttribute("errorMessage", "Error updating the database");
                        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("errorMessage", "Incorrect old password");
                    request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Passwords do not match");
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "User not logged in");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        }
    }
}

package Controller;

import DAO.UserDAO;
import Model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
@WebServlet("/verify")
public class VerifyController extends HttpServlet {

    private static final long serialVersionUID = 1L;

  
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession mySession = request.getSession();
        String otp = (String) mySession.getAttribute("otp");
        String email = (String) mySession.getAttribute("email");
        String value = request.getParameter("otp");
        RequestDispatcher dispatcher = null;

        if (email == null || otp == null || value == null) {
            // Thực hiện xử lý khi email hoặc OTP không tồn tại
            request.setAttribute("message", "Email or OTP does not exist.");
            dispatcher = request.getRequestDispatcher("verify.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (value.equals(otp)) {
            // Mã OTP nhập vào đúng
            // Thực hiện các hành động cần thiết ở đây, ví dụ: cập nhật trạng thái xác thực
            // Sau đó chuyển hướng người dùng đến trang thành công
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        } else {
            // Mã OTP nhập vào không đúng
            // Thông báo cho người dùng và chuyển hướng đến trang verify.jsp để nhập lại OTP
            request.setAttribute("message", "Wrong OTP.");
            dispatcher = request.getRequestDispatcher("verify.jsp");
            dispatcher.forward(request, response);
        }
    }
}

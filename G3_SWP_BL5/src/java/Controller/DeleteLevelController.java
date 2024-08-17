package Controller;

import DAO.LevelDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "DeleteLevelController", urlPatterns = {"/DeleteLevelController"})
public class DeleteLevelController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (userSession == null || userSession.getRole().getRole_id() != 3) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            int levelId = Integer.parseInt(request.getParameter("levelId"));

            LevelDAO levelDAO = new LevelDAO();
            boolean isLevelHasTopics = levelDAO.isLevelHasTopics(levelId);

            if (isLevelHasTopics) {
                request.setAttribute("errorMessage", "This level cannot be deleted because it has associated topics.");
                request.getRequestDispatcher("ManageLevel").forward(request, response);
            } else {
                levelDAO.deleteLevel(levelId);
                response.sendRedirect("ManageLevel");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

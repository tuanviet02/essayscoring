package Controller;

import DAO.LevelDAO;
import Model.Level;
import Model.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "CreateLevelController", urlPatterns = {"/CreateLevel"})
public class CreateLevelController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateLevelController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateLevelController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (userSession == null || userSession.getRole().getRole_id() != 3) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            String levelNameParam = request.getParameter("levelName");
            Level newLevel = new Level();
            newLevel.setLevelName(levelNameParam);
            LevelDAO dao = new LevelDAO();
            if (!dao.isLevelNameExists(levelNameParam)) {
                dao.addLevel(newLevel);
                response.sendRedirect("ManageLevel");
            } else {
                request.setAttribute("errorMessage", "Level name existed");
                RequestDispatcher dispatcher = request.getRequestDispatcher("createLevel.jsp");
                dispatcher.forward(request, response);
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

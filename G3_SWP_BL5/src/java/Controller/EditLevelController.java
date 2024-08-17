package Controller;

import DAO.LevelDAO;
import Model.Level;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "EditLevelController", urlPatterns = {"/EditLevel"})
public class EditLevelController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditLevelController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditLevelController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute("user");
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (userSession == null || userSession.getRole().getRole_id() != 3) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            String levelIdParam = request.getParameter("levelId");
            int levelId = Integer.parseInt(levelIdParam);
            LevelDAO dao = new LevelDAO();
            Level levelToGet = dao.getLevelById(levelId);
            request.setAttribute("level", levelToGet);
            request.getRequestDispatcher("editLevel.jsp").forward(request, response);
        }
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
            String levelIdParam = request.getParameter("levelId");
            String levelNameParam = request.getParameter("levelName");
            int levelId = Integer.parseInt(levelIdParam);
            Level levelToUpdate = new Level();
            levelToUpdate.setLevelId(levelId);
            levelToUpdate.setLevelName(levelNameParam);
            LevelDAO dao = new LevelDAO();
            dao.updateLevel(levelToUpdate);
            response.sendRedirect("ManageLevel");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

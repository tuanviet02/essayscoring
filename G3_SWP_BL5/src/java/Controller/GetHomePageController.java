package Controller;

import DAO.EvaluatorEssayDAO;
import Model.EvaluatorEssay;
import Model.User;
import Model.WriterEssay;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "GetHomePageController", urlPatterns = {"/Homepage"})
public class GetHomePageController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetHomePageController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetHomePageController at " + request.getContextPath() + "</h1>");
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
        if (userSession == null || userSession.getRole().getRole_id() != 4) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {

            int limit = 5;
            EvaluatorEssayDAO essayDAO = new EvaluatorEssayDAO();
            String filterOption = request.getParameter("filterOption") == null ? "popular" : request.getParameter("filterOption");
            if (filterOption == null) {
                List<EvaluatorEssay> popularEssays = essayDAO.getListNRecentEvaluatorEssays(limit);
                request.setAttribute("popularEssays", popularEssays);
                request.getRequestDispatcher("homepage.jsp").forward(request, response);
            }
            if (filterOption != null && filterOption.equals("popular")) {
                List<EvaluatorEssay> popularEssays = essayDAO.getListNRecentEvaluatorEssays(limit);
                request.setAttribute("popularEssays", popularEssays);
                request.getRequestDispatcher("homepage.jsp").forward(request, response);
            } else {
                List<WriterEssay> topEssays = essayDAO.getListTopNEssaysByNumberOfWriters(limit);
                request.setAttribute("topEssays", topEssays);
                request.getRequestDispatcher("homepage.jsp").forward(request, response);
            }
        }
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

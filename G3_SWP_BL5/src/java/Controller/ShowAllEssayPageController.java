/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.EvaluatorEssayDAO;
import DAO.WriterEssayDAO;
import Model.EvaluatorEssay;
import Model.User;
import Model.WriterEssay;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "ShowAllEssayPageController", urlPatterns = {"/ShowAllEssayPageController"})
public class ShowAllEssayPageController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowAllEssayPageController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowAllEssayPageController at " + request.getContextPath() + "</h1>");
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
            User user = (User) session.getAttribute("user");

            // Kiểm tra xem người dùng đã đăng nhập chưa
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            if (user.getRole().getRole_id() == 4) {
                int userID = user.getUserID();
                EvaluatorEssayDAO essayDAO = new EvaluatorEssayDAO();
                int minEssays = 1;
                int maxEssays = 20;

                if (request.getParameter("minEssays") != null && request.getParameter("maxEssays") != null) {
                    minEssays = Integer.parseInt(request.getParameter("minEssays"));
                    maxEssays = Integer.parseInt(request.getParameter("maxEssays"));
                }
                double averageScoreOfAll = essayDAO.getAverageScoreByUserID(userID);
                averageScoreOfAll = Math.round(averageScoreOfAll * 10.0) / 10.0;

                List<EvaluatorEssay> evaluatorEssaysInRange = essayDAO.getEvaluatorEssaysInRangeByUserID(userID, minEssays, maxEssays);
                double totalScore = 0.0;
                int totalCount = evaluatorEssaysInRange.size();
                for (EvaluatorEssay evaluatorEssay : evaluatorEssaysInRange) {
                    totalScore += evaluatorEssay.getScore();
                }
                if (totalCount > 0) {
                    double averageScore = totalScore / totalCount;
                    averageScore = Math.round(averageScore * 10.0) / 10.0;

                    request.setAttribute("averageScoreInRange", averageScore);
                }
                List<EvaluatorEssay> getAll = essayDAO.getListEvaluatorEssayByUserID(userID);
                request.setAttribute("evaluatorEssaysInRange", evaluatorEssaysInRange);
                request.setAttribute("n", totalCount);
                request.setAttribute("nTotal", getAll.size());

                request.setAttribute("averageScoreOfRecent", averageScoreOfAll);
                request.setAttribute("evaluatorEssaysAll", getAll);
                RequestDispatcher dispatcher = request.getRequestDispatcher("userEssayScore.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        }
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.EvaluatorEssayDAO;
import Model.EvaluatorEssay;
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
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ScoreRateController", urlPatterns = {"/ScoreRateController"})
public class ScoreRateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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

                Map<String, List<EvaluatorEssay>> evaluatorEssaysMap = new HashMap<>();
                Map<String, Integer> totalCountMap = new HashMap<>();
                Map<String, List<EvaluatorEssay>> evaluator20EssaysInRangeMap = new HashMap<>();
                Map<String, Integer> total20CountMap = new HashMap<>();

                int[][] scoreRanges = {{0, 3}, {4, 6}, {7, 8}, {9, 10}};

                for (int[] range : scoreRanges) {
                    int minScore = range[0];
                    int maxScore = range[1];

                    List<EvaluatorEssay> evaluatorEssaysInRange = essayDAO.getEvaluatorEssaysByScoreRange(userID, minScore, maxScore);
                    int totalCount = essayDAO.countAllEvaluatorEssayByScoreRange(userID, minScore, maxScore);

                    List<EvaluatorEssay> evaluator20EssaysInRange = essayDAO.get20EvaluatorEssaysByScoreRange(userID, minScore, maxScore);
                    int total20Count = essayDAO.count20EvaluatorEssayByScoreRange(userID, minScore, maxScore);

                    String rangeKey = minScore + " - " + maxScore;
                    evaluatorEssaysMap.put(rangeKey, evaluatorEssaysInRange);
                    totalCountMap.put(rangeKey, totalCount);
                    evaluator20EssaysInRangeMap.put(rangeKey, evaluator20EssaysInRange);
                    total20CountMap.put(rangeKey, total20Count);

                }
                String adjective = "";

                int count03 = totalCountMap.get("0 - 3");
                int count46 = totalCountMap.get("4 - 6");
                int count78 = totalCountMap.get("7 - 8");
                int count910 = totalCountMap.get("9 - 10");

                if (count910 > count03) {
                    adjective = "ngu";
                } else if (count46 > count78) {
                    adjective = "thoong minh";
                }
                request.setAttribute("adjective", adjective);
                request.setAttribute("evaluatorEssaysMap", evaluatorEssaysMap);
                request.setAttribute("totalCountMap", totalCountMap);
                request.setAttribute("evaluator20EssaysMap", evaluator20EssaysInRangeMap);
                request.setAttribute("total20CountMap", total20CountMap);

                RequestDispatcher dispatcher = request.getRequestDispatcher("userEssay.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("login.jsp");

            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

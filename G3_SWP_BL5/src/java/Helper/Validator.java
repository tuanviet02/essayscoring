///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Helper;
//
//import Common.Constant;
//import DAO.PageDAO;
//import Model.User;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// *
// * @author THTP
// */
//public class Validator {
//
//    public static boolean checkPermission(String url, String option, HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String userRole = Constant.USER_GUEST;
//        HttpSession session = req.getSession();
//        try {
//            User user = (User) session.getAttribute("user");
//            if (user != null) {
//                userRole = String.valueOf(user.getRole_id());
//            }
//            if (new PageDAO().isApproval(url, userRole, option) == false) {
//                req.getRequestDispatcher("./admin/pages-error.html").forward(req,resp);
//                return false;
//            }
//            req.setAttribute("user_passed", user);
//            return true;
//        } catch (Exception e) {
//            resp.sendRedirect("./admin/pages-error.html");
//            return false;
//        }
//    }
//
//}

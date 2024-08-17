/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validate;

import DAO.UserDAO;

/**
 *
 * @author nguye
 */
public class Validate {

    private static final String NUMBER_PHONE_REGEX = "(0)[0-9]{9,10}";
    private static final String PASSWORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    private static final UserDAO userDao = new UserDAO();

    public boolean isRequire(String value) {
        return value == null || "".equals(value);
    }

    public boolean isNumberPhone(String value) {
        return value.matches(NUMBER_PHONE_REGEX);
    }

    public boolean isPassword(String value) {
        return value.matches(PASSWORD_REGEX);
    }

    public boolean isEmail(String value) {
        return value.matches(EMAIL_REGEX);
    }

    public boolean checkEmail(String value) {
        return userDao.checkEmail(value);
    }

}

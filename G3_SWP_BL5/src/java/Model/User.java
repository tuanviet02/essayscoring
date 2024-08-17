/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 */
public class User {

    private int userID;
    private String email;
    private String password;
    private Date createDate;
    private String fullName;
    private String phone;
    private String avatar;
    private boolean status;
    private boolean gender;
    private boolean is_verified;
    private String verification_code;
    private Role role;  
    private int roleId;

    private boolean registered;

    // Getter và setter cho thuộc tính `registered`
    public boolean isRegistered() {
        return registered;
    }

    public User(int userID, String fullName) {
        this.userID = userID;
        this.fullName = fullName;
    }

    public User(int userID) {
        this.userID = userID;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public User(int userID, String email, String password, Date createDate, String fullName, String phone, String avatar, boolean status, boolean gender, boolean is_verified, String verification_code, Role role) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.createDate = createDate;
        this.fullName = fullName;
        this.phone = phone;
        this.avatar = avatar;
        this.status = status;
        this.gender = gender;
        this.is_verified = is_verified;
        this.verification_code = verification_code;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(int userID, String email, String password, Date createDate, String fullName, String phone, String avatar, boolean status, boolean gender, boolean is_verified, String verification_code, Role role, int roleId, boolean registered) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.createDate = createDate;
        this.fullName = fullName;
        this.phone = phone;
        this.avatar = avatar;
        this.status = status;
        this.gender = gender;
        this.is_verified = is_verified;
        this.verification_code = verification_code;
        this.role = role;
        this.roleId = roleId;
        this.registered = registered;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isGender() {
        return gender;
    }

    public User(int userID, String email, String password, Date createDate, String fullName, String phone, String avatar, boolean status, boolean gender, boolean is_verified, String verification_code) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.createDate = createDate;
        this.fullName = fullName;
        this.phone = phone;
        this.avatar = avatar;
        this.status = status;
        this.gender = gender;
        this.is_verified = is_verified;
        this.verification_code = verification_code;

    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", email=" + email + ", password=" + password + ", createDate=" + createDate + ", fullName=" + fullName + ", phone=" + phone + ", avatar=" + avatar + ", status=" + status + ", gender=" + gender + ", is_verified=" + is_verified + ", verification_code=" + verification_code + ", role=" + role + '}';
    }

}

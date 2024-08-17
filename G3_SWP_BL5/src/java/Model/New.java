/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;


/**
 *
 * @author nguye
 */
public class New {

    private int newId;
    private String title;
    private String content;
    private Date createdDate;
    private Date updatedDate;
    private int user_id;
    private String status;
    private User user;

    public New() {
    }

    public New(int newId, String title, String content, Date createdDate, Date updatedDate, int user_id, String status, User user) {
        this.newId = newId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.user_id = user_id;
        this.status = status;
        this.user = user;
    }

    public int getNewId() {
        return newId;
    }

    public void setNewId(int newId) {
        this.newId = newId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_date() {
        return createdDate;
    }

    public void setCreated_date(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdated_date() {
        return updatedDate;
    }

    public void setUpdated_date(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "New{" + "newId=" + newId + ", title=" + title + ", content=" + content + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", user_id=" + user_id + ", status=" + status + ", user=" + user + '}';
    }

}

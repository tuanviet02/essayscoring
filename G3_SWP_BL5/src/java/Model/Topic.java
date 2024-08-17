
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.Date;

public class Topic {
    private int topicId;
    private String title;
    private Date createdDate;
    private Date updatedDate;
    private String description;
    private Level level;
    private EssayType type;
    private String status;
    private User user;
    private ArrayList<Criteria> criteriaList = new ArrayList<>();

    public Topic() {
    }

    public Topic(int topicId, String title, Date createdDate, Date updatedDate, String description, Level level, EssayType type, String status, User user) {
        this.topicId = topicId;
        this.title = title;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.description = description;
        this.level = level;
        this.type = type;
        this.status = status;
        this.user = user;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public EssayType getType() {
        return type;
    }

    public void setType(EssayType type) {
        this.type = type;
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

    public ArrayList<Criteria> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(ArrayList<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }
    
    
}


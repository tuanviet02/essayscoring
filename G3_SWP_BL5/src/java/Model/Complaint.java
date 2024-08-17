/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Sang
 */
public class Complaint {
    private int complaintID;
    private String complaintTitle;
    private String content;
    private Date createdDate;
    private EvaluatorEssay evaluatorEssay;
    private String status;

    public Complaint() {
    }

    public Complaint(int complaintID, String complaintTitle, String content, Date createdDate, EvaluatorEssay evaluatorEssay, String status) {
        this.complaintID = complaintID;
        this.complaintTitle = complaintTitle;
        this.content = content;
        this.createdDate = createdDate;
        this.evaluatorEssay = evaluatorEssay;
        this.status = status;
    }

    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }

    public String getComplaintTitle() {
        return complaintTitle;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public EvaluatorEssay getEvaluatorEssay() {
        return evaluatorEssay;
    }

    public void setEvaluatorEssay(EvaluatorEssay evaluatorEssay) {
        this.evaluatorEssay = evaluatorEssay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}

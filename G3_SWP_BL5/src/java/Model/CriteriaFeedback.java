/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Sang
 */
public class CriteriaFeedback {
    private int criteriaFeedbackID;
    private Criteria criteria;
    private EvaluatorEssay evaluatorEssay;
    private String feedbackContent;

    public CriteriaFeedback() {
    }

    public CriteriaFeedback(int criteriaFeedbackID, Criteria criteria, EvaluatorEssay evaluatorEssay, String feedbackContent) {
        this.criteriaFeedbackID = criteriaFeedbackID;
        this.criteria = criteria;
        this.evaluatorEssay = evaluatorEssay;
        this.feedbackContent = feedbackContent;
    }

    public int getCriteriaFeedbackID() {
        return criteriaFeedbackID;
    }

    public void setCriteriaFeedbackID(int criteriaFeedbackID) {
        this.criteriaFeedbackID = criteriaFeedbackID;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public EvaluatorEssay getEvaluatorEssay() {
        return evaluatorEssay;
    }

    public void setEvaluatorEssay(EvaluatorEssay evaluatorEssay) {
        this.evaluatorEssay = evaluatorEssay;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

   
    
}

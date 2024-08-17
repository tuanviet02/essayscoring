/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Sang
 */
public class EvaluatorEssay {
    private int evaluatorEssayID;
    private WriterEssay writerEssay;
    private String feedbackContent;
    private double score;

    private User evaluator;
    private ArrayList<CriteriaFeedback> criteriaFeedbackList = new ArrayList<>();

    public EvaluatorEssay() {
    }

    public EvaluatorEssay(int evaluatorEssayID, WriterEssay writerEssay, String feedbackContent, int score, User evaluator) {
        this.evaluatorEssayID = evaluatorEssayID;
        this.writerEssay = writerEssay;
        this.feedbackContent = feedbackContent;
        this.score = score;
        this.evaluator = evaluator;
    }

    public int getEvaluatorEssayID() {
        return evaluatorEssayID;
    }

    public void setEvaluatorEssayID(int evaluatorEssayID) {
        this.evaluatorEssayID = evaluatorEssayID;
    }

    public WriterEssay getWriterEssay() {
        return writerEssay;
    }

    public void setWriterEssay(WriterEssay writerEssay) {
        this.writerEssay = writerEssay;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }



    public User getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(User evaluator) {
        this.evaluator = evaluator;
    }

    public ArrayList<CriteriaFeedback> getCriteriaFeedbackList() {
        return criteriaFeedbackList;
    }

    public void setCriteriaFeedbackList(ArrayList<CriteriaFeedback> criteriaFeedbackList) {
        this.criteriaFeedbackList = criteriaFeedbackList;
    }
    
    
}

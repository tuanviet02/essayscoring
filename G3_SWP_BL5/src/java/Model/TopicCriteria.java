/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AnhTH
 */
public class TopicCriteria {
   private int topicCriteriaID;
    private Topic topic;
            private Criteria criteria;

    public TopicCriteria() {
    }

    public TopicCriteria(int topicCriteriaID, Topic topic, Criteria criteria) {
        this.topicCriteriaID = topicCriteriaID;
        this.topic = topic;
        this.criteria = criteria;
    }

    public int getTopicCriteriaID() {
        return topicCriteriaID;
    }

    public void setTopicCriteriaID(int topicCriteriaID) {
        this.topicCriteriaID = topicCriteriaID;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
            
}

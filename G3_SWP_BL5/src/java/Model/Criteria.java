/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Sang
 */
public class Criteria {
    private int criteriaID;
    private String criteriaName;
    private String description;

    public Criteria() {
    }

    public Criteria(int criteriaID, String criteriaName, String description) {
        this.criteriaID = criteriaID;
        this.criteriaName = criteriaName;
        this.description = description;
    }

    public int getCriteriaID() {
        return criteriaID;
    }

    public void setCriteriaID(int criteriaID) {
        this.criteriaID = criteriaID;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}

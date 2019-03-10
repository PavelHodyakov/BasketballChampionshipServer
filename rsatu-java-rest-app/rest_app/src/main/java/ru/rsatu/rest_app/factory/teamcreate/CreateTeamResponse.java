/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.factory.teamcreate;

import ru.rsatu.rest_app.pojo.ResultStatus;
import ru.rsatu.rest_app.pojo.Student;
import ru.rsatu.rest_app.pojo.TeamPojo;

/**
 *
 * @author npetrov
 */
public class CreateTeamResponse {
    
    private String teamName;
    private String city;
    private ResultStatus resultStatus;

    public String getCity() {
        return city;
    }

    public CreateTeamResponse(String teamName, String city, ResultStatus resultStatus) {
        this.teamName = teamName;
        this.city = city;
        this.resultStatus = resultStatus;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTeam() {
        return teamName;
    }

    public void setTeam(String team) {
        this.teamName = team;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }
    
}

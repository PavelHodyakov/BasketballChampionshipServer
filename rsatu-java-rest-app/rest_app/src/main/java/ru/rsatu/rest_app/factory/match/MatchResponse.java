/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.factory.match;

import java.util.Date;
import java.util.List;
import ru.rsatu.rest_app.pojo.ResultStatus;

/**
 *
 * @author pavel
 */
public class MatchResponse {
    private String teamHost;
    private String teamGuest;
    private String referee;
    private String date;
    int teamHostScore;
    int teamGuestScore;
    private ResultStatus resultStatus;
    
    
    public MatchResponse(String teamHost, String teamGuest, String fitstNameReferee, String secondNameReferee, String date, int teamHostScore, int teamGuestScore) {
        this.teamHost = teamHost;
        this.teamGuest = teamGuest;
        this.referee = fitstNameReferee + ' ' + secondNameReferee;
        this.date = date;
        this.teamGuestScore = teamGuestScore;
        this.teamHostScore = teamHostScore;
        //this.resultStatus = resultStatus;
    }

    public MatchResponse(String teamHost, String teamGuest, String firstNameReferee, String secondNameReferee , String date, ResultStatus resultStatus) {
        this.teamHost = teamHost;
        this.teamGuest = teamGuest;
        this.referee = firstNameReferee + ' ' +secondNameReferee;
        this.date = date;
        this.resultStatus = resultStatus;
    }

    public String getTeamHost() {
        return teamHost;
    }

    public String getTeamGuest() {
        return teamGuest;
    }

    public String getReferee() {
        return referee;
    }

    public String getDate() {
        return date;
    }

    public int getTeamHostScore() {
        return teamHostScore;
    }

    public int getTeamGuestScore() {
        return teamGuestScore;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }
    
    
    
    
}

//public class ListMatchResponse{
//    List<MatchResponse> response;
//
//    public ListMatchResponse(List<MatchResponse> response) {
//        this.response = response;
//    }
//    
//    
//}

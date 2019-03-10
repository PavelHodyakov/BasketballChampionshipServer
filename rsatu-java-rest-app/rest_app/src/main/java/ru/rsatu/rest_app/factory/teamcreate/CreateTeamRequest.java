/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.factory.teamcreate;

import ru.rsatu.rest_app.pojo.TeamPojo;

/**
 *
 * @author npetrov
 */
public class CreateTeamRequest {
    
    private String team;
    private String city;

    public CreateTeamRequest(String team, String city) {
        this.team = team;
        this.city = city;
    }
    

    public void setTeam(String team) {
        this.team = team;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTeam() {
        return team;
    }

    public String getCity() {
        return city;
    }

    
    
}

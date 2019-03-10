/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.factory.refereecreate;

import ru.rsatu.rest_app.pojo.ResultStatus;

/**
 *
 * @author pavel
 */
public class CreateRefereeResponse {
    private String firstName;
    private String secondName;
    private String city;
    private ResultStatus resultStatus;
    
    public CreateRefereeResponse(String firstName, String secondName, String city, ResultStatus resultStatus) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.city = city;
        this.resultStatus = resultStatus;
    }
}

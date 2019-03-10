/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.factory.Login;

/**
 *
 * @author pavel
 */
public class LoginResponse {
    private Boolean result;

    public LoginResponse() {
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public Boolean getResult() {
        return result;
    }

    public String getRights() {
        return rights;
    }
    private String rights;

    public LoginResponse(Boolean result, String rights) {
        this.result = result;
        this.rights = rights;
    }
    
        
}

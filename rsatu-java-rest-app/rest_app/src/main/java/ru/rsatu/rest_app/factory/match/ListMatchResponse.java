/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.factory.match;

import java.util.List;


public class ListMatchResponse{
    List<MatchResponse> response;
    
    public ListMatchResponse(List<MatchResponse> response) {
        this.response = response;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.pojo;

/**
 *
 * @author pavel
 */
public class TeamPojo {
    String name;
    String city;

    public TeamPojo() {
    }

    public TeamPojo(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}

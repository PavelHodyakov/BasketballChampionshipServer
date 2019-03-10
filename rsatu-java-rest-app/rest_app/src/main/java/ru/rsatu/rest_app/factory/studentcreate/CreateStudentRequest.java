/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.factory.studentcreate;

import ru.rsatu.rest_app.pojo.Student;

/**
 *
 * @author npetrov
 */
public class CreateStudentRequest {
    
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
}

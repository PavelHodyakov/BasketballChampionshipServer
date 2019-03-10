/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.factory.studentcreate;

import ru.rsatu.rest_app.pojo.ResultStatus;
import ru.rsatu.rest_app.pojo.Student;

/**
 *
 * @author npetrov
 */
public class CreateStudentResponse {
    
    private Student student;
    private ResultStatus resultStatus;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }
    
}

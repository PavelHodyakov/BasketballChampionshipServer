/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.factory.studentcreate;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ru.rsatu.rest_app.Tables.Team;
import ru.rsatu.rest_app.constants.Constants;
import ru.rsatu.rest_app.factory.teamcreate.CreateTeamRequest;
import ru.rsatu.rest_app.pojo.RestAppException;
import ru.rsatu.rest_app.pojo.ResultStatus;
import ru.rsatu.rest_app.pojo.Student;

/**
 *
 * @author npetrov
 */
public class CreateStudentFactory {
    
    public CreateStudentResponse createStudent(EntityManager em, CreateStudentRequest request)
                  throws RestAppException{
        CreateStudentResponse result = new CreateStudentResponse();
        try {
            Student student = request.getStudent();
            em.persist(student);
            ResultStatus resultStatus = new ResultStatus();
            resultStatus.setErrorCode(Constants.ERROR_CODE_OK);
            resultStatus.setErrorText(Constants.ERROR_TEXT_OK);
            result.setResultStatus(resultStatus);
            result.setStudent(student);
        } catch (Exception ex) {
            throw new RestAppException("Unable to create student");
        }
        return result;
    }
        
}

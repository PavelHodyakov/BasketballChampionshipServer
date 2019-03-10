/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.rest;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;
import ru.rsatu.rest_app.Tables.Match;
import ru.rsatu.rest_app.factory.Login.LoginFactory;
import ru.rsatu.rest_app.factory.Login.LoginResponse;
import ru.rsatu.rest_app.Tables.Team;
import ru.rsatu.rest_app.constants.Constants;
import ru.rsatu.rest_app.factory.match.ListMatchResponse;
import ru.rsatu.rest_app.factory.match.MatchFactory;
import ru.rsatu.rest_app.factory.match.MatchResponse;
import ru.rsatu.rest_app.factory.refereecreate.CreateRefereeFactory;
import ru.rsatu.rest_app.factory.refereecreate.CreateRefereeResponse;
import ru.rsatu.rest_app.factory.studentcreate.CreateStudentFactory;
import ru.rsatu.rest_app.factory.studentcreate.CreateStudentRequest;
import ru.rsatu.rest_app.factory.studentcreate.CreateStudentResponse;
import ru.rsatu.rest_app.factory.teamcreate.CreateTeamFactory;
import ru.rsatu.rest_app.factory.teamcreate.CreateTeamRequest;
import ru.rsatu.rest_app.factory.teamcreate.CreateTeamResponse;
import ru.rsatu.rest_app.pojo.RestAppException;
import ru.rsatu.rest_app.pojo.ResultStatus;
import ru.rsatu.rest_app.pojo.TeamPojo;

/**
 *
 * @author npetrov
 */
@Stateless
@Path("/gate")
public class RestGate {

    @PersistenceContext(unitName = "rest_app")
    private EntityManager entityManager;
    
    @POST
    @Path("testEM")
    public String testEM() {
        if (entityManager == null) {
            return "bad";
        }
        return "yay";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("createStudent")
    public CreateStudentResponse createStudent(CreateStudentRequest request) {
        CreateStudentResponse response = null;
        try {
            response = new CreateStudentFactory().createStudent(entityManager, request);
        } catch (RestAppException ex) {
            response = new CreateStudentResponse();
            ResultStatus resultStatus = new ResultStatus();
            resultStatus.setErrorCode(Constants.ERROR_CODE_ERROR);
            resultStatus.setErrorText(Constants.ERROR_TEXT_ERROR);
            response.setResultStatus(resultStatus);
        }
        return response;
    }
    
        
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getMatches")
    public List<MatchResponse> getData() throws JSONException, RestAppException{
        //Query query = entityManager.createNamedQuery("Match.findAll");
        //List<Match> list = query.getResultList();
        //return list;        
        MatchFactory matchFactory = new MatchFactory();
        List<MatchResponse> response = matchFactory.getMatches(entityManager);
        return response;
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/excludeTeam")
    public CreateTeamResponse excludeTeam(String team) throws JSONException, RestAppException{
        JSONObject json = new JSONObject(team);
        CreateTeamFactory createTeamFactory = new CreateTeamFactory();
        
        CreateTeamResponse response = createTeamFactory.excludeTeam(entityManager,
                json.getString("name"), json.getString("city"));
        return response;
    }
    
        
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getTeam")
    public List<String> getTeam() throws JSONException, RestAppException{
        //Query query = entityManager.createNamedQuery("Match.findAll");
        //List<Match> list = query.getResultList();
        //return list;        
        CreateTeamFactory teamFactory = new CreateTeamFactory();
        List<String> response = teamFactory.getTeams(entityManager);
        return response;
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createReferee")
    public CreateRefereeResponse createReferee(String referee) throws JSONException, RestAppException{
        JSONObject json = new JSONObject(referee);
        CreateRefereeFactory createRefereeFactory = new CreateRefereeFactory();
      
        CreateRefereeResponse response = createRefereeFactory.addReferee(entityManager, json.getString("city"),
                json.getString("firstName"), json.getString("secondName")," ", 2);
        return response;
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getReferee")
    public List<String> getReferee() throws JSONException, RestAppException{
        //Query query = entityManager.createNamedQuery("Match.findAll");
        //List<Match> list = query.getResultList();
        //return list;        
        CreateRefereeFactory refereeFactory = new CreateRefereeFactory();
        List<String> response = refereeFactory.getReferee(entityManager);
        return response;
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/excludeReferee")
    public CreateRefereeResponse excludeReferee(String referee) throws JSONException, RestAppException{
        JSONObject json = new JSONObject(referee);
        CreateRefereeFactory createRefereeFactory = new CreateRefereeFactory();
      
        CreateRefereeResponse response = createRefereeFactory.excludeReferee(entityManager, json.getString("city"),
                json.getString("firstName"), json.getString("secondName")," ", 2);
        return response;
    }
        
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createMatch")
    public MatchResponse createMatch(String match) throws JSONException, RestAppException{
        JSONObject json = new JSONObject(match);
        MatchFactory matchFactory = new MatchFactory();
        String referee = json.getString("referee");
        String[] ref = referee.split(" ");
        String refereeFirstName = ref[0];
        String refereeSecondName = ref[1];
        MatchResponse response = matchFactory.createMatch(entityManager,
                json.getString("teamHost"), json.getString("teamGuest"),
                refereeFirstName, refereeSecondName, json.getString("date"));
        return response;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    public LoginResponse login(String loginJson) throws JSONException, RestAppException{
        JSONObject json = new JSONObject(loginJson);
        LoginFactory loginFactory = new LoginFactory();
        
        LoginResponse response = loginFactory.loginUser(entityManager,
                json.getString("login"), json.getString("password"));
        return response;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("registry")
    public LoginResponse addUser(String loginJson) throws JSONException, RestAppException{
        JSONObject json = new JSONObject(loginJson);
        LoginFactory loginFactory = new LoginFactory();
        
        LoginResponse response = loginFactory.registryUser(entityManager,
                json.getString("login"), json.getString("password"));
        return response;
    }
    
}

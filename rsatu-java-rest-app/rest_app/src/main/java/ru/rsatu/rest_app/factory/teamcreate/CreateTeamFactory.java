/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.factory.teamcreate;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ru.rsatu.rest_app.Tables.Team;
import ru.rsatu.rest_app.constants.Constants;
import ru.rsatu.rest_app.factory.studentcreate.CreateStudentResponse;
import ru.rsatu.rest_app.pojo.RestAppException;
import ru.rsatu.rest_app.pojo.ResultStatus;
import ru.rsatu.rest_app.pojo.Student;
import ru.rsatu.rest_app.pojo.TeamPojo;

/**
 *
 * @author npetrov
 */
public class CreateTeamFactory {
    
    public CreateTeamResponse createTeam(EntityManager em, String teamName, String cityName) throws RestAppException{
//        CreateTeamResponse result = new CreateTeamResponse();
//        try {
//            TeamPojo team = request.getTeamPojo();
//            em.persist(team);
//            ResultStatus resultStatus = new ResultStatus();
//            resultStatus.setErrorCode(Constants.ERROR_CODE_OK);
//            resultStatus.setErrorText(Constants.ERROR_TEXT_OK);
//            result.setResultStatus(resultStatus);
//            result.setStudent(team);
//        } catch (Exception ex) {
//            throw new RestAppException("Unable to create student");
//        }
//        return result;
//    }
           
        List<Object> ciy = null;
        CreateTeamResponse result;
        try {
            List<Object> nm = null;
            Query com = em.createNativeQuery("Select * From Team Where(TeamName=:teamname)");
            com.setParameter("teamname", teamName);
            nm = com.getResultList();
            if (nm.size() == 0) {//если нет такой команды то добавляем и проверяем город на наличие
                //проверяем на наличие города в списке, если нет то добавляем город
                Query find = em.createNativeQuery("Select * From City Where(CityName=:cityname)");
                find.setParameter("cityname", cityName);
                ciy = find.getResultList();
                if (ciy.size() == 0) {//если такого города нет, то добавляем город 
                    Query qw = em.createNativeQuery("Insert Into City(CityName) Values(:cityname)");
                    qw.setParameter("cityname", cityName);
                    qw.executeUpdate();//используется при добавлении
                }
                //Добавляем команду
                //ищем id города этой команды
                Query rt = em.createNativeQuery("Select idCity From City Where(CityName=:cit)");
                rt.setParameter("cit", cityName);
                int id = (Integer) rt.getResultList().get(0);
                Query addcity = em.createNativeQuery("Insert Into Team(TeamName,TeamCityId,Flag) Values(:name,:id,:flag)");
                addcity.setParameter("id", id);
                addcity.setParameter("name", teamName);//добавили команду
                addcity.setParameter("flag", true);//добавляем показатель активности
                addcity.executeUpdate();

                ResultStatus resultStatus = new ResultStatus();
                resultStatus.setErrorCode(Constants.ERROR_CODE_OK);
                resultStatus.setErrorText(Constants.ERROR_TEXT_OK);
                result = new CreateTeamResponse(teamName, cityName, resultStatus);
            } else {
                ResultStatus resultStatus = new ResultStatus();
                resultStatus.setErrorCode(Constants.ERROR_CODE_ERROR);
                resultStatus.setErrorText(Constants.ERROR_TEXT_ERROR);
                result = new CreateTeamResponse(teamName, cityName, resultStatus);
            //сообщение об ошибке                        
            //WarningMessage("Команда с таким названием уже существует");
            }
        } catch (Exception e) {
            throw new RestAppException("Unable to create student");
        }
        return result;
    }
    
    /**
     * Проверка команды на существование 
     * @param em
     * @param teamName
     * @return true - значит пустой
     */
    private boolean checkCommandForExistence(EntityManager em, String teamName){
        Query com = em.createNativeQuery("Select * From Team Where(TeamName=:teamname)");
        com.setParameter("teamname", teamName);
        return com.getResultList().isEmpty();
    }
    
     private boolean checkMatchForExistence(EntityManager em, int id ){
        Query m = em.createNativeQuery("Select * From Match where ((TeamHostId=:g OR TeamGuestId=:g) AND HostScore is null AND GuestScore is null)");
        m.setParameter("g", id);
        return m.getResultList().isEmpty();
    }
    
    public CreateTeamResponse excludeTeam(EntityManager em, String teamName, String cityName) throws RestAppException{
        CreateTeamResponse result=null;
        try{
            if(!checkCommandForExistence(em, teamName)){
                Query v = em.createNativeQuery("Select idTeam From Team where(TeamName=:tn)");
                v.setParameter("tn", teamName);
                int gi = (int) v.getResultList().get(0);
                if(checkMatchForExistence(em, gi)){
                    Query t = em.createNativeQuery("Delete from `Match` where ((TeamHostId=:g OR TeamGuestId=:g) AND HostScore is null AND GuestScore is null)");
                    t.setParameter("g", gi);
                    t.executeUpdate();
                    Query tq = em.createNativeQuery("Update Team set Flag=false where (idTeam=:g)");
                    tq.setParameter("g", gi);
                    tq.executeUpdate();
                    ResultStatus resultStatus = new ResultStatus();
                    resultStatus.setErrorCode(Constants.ERROR_CODE_OK);
                    resultStatus.setErrorText(Constants.ERROR_TEXT_OK);
                    result = new CreateTeamResponse(teamName, cityName, resultStatus);
                }
            } else{
                ResultStatus resultStatus = new ResultStatus();
                resultStatus.setErrorCode(Constants.ERROR_CODE_ERROR);
                resultStatus.setErrorText(Constants.ERROR_TEXT_ERROR);
                result = new CreateTeamResponse(teamName, cityName, resultStatus);
            }
        } catch(Exception e){
            new RestAppException("Unable to exclude team");
        }
        return result;
    }
    
    public List<String> getTeams(EntityManager em){
        Query query = em.createNativeQuery("Select TeamName from Team");
        List<String>teamList = query.getResultList();
//        List<MatchResponse> res = new ArrayList<>();
        System.out.println("");
        return teamList;
    }
}

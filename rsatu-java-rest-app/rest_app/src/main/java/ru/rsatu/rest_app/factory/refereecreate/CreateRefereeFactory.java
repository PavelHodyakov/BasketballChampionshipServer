/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.factory.refereecreate;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ru.rsatu.rest_app.Tables.Match;
import ru.rsatu.rest_app.Tables.Referee;
import ru.rsatu.rest_app.constants.Constants;
import ru.rsatu.rest_app.factory.match.MatchResponse;
import ru.rsatu.rest_app.factory.teamcreate.CreateTeamResponse;
import ru.rsatu.rest_app.pojo.RestAppException;
import ru.rsatu.rest_app.pojo.ResultStatus;

/**
 *
 * @author pavel
 */
public class CreateRefereeFactory {
    
    public CreateRefereeResponse addReferee(EntityManager em, String city, String fName, String sName, String pName, int category) throws RestAppException{
        CreateRefereeResponse result = null;
        try {
            Query find = em.createNativeQuery("Select * From City Where(CityName=:cityname)");
            find.setParameter("cityname", city);
            //find.addEntity(City.class);
            List<Object> ciy = find.getResultList();
            if (ciy.size() == 0) {//если такого города нет, то добавляем его и так же добавляем судью 
                Query qw = em.createNativeQuery("Insert Into City(CityName) Values(:cityname)");
                qw.setParameter("cityname", city);
                qw.executeUpdate();
                //получаем id этого города
                Query cityid = em.createNativeQuery("Select idCity From City Where(CityName=:city)");
                cityid.setParameter("city", city);
                int citid = (int) cityid.getResultList().get(0);
                //добавляем рефери
                Query add = em.createNativeQuery("Insert Into Referee(Category,RefereeCityId,RefereeFirstName, RefereeSecondName, RefereePatronymic, FlagRef) Values(:categ,:refcitid,:fname,:sname,:pname,:flagref)");
                add.setParameter("fname", fName);
                add.setParameter("sname", sName);
                add.setParameter("pname", pName);
                add.setParameter("flagref", true);
                add.setParameter("refcitid", citid);
                add.setParameter("categ", category);
                add.executeUpdate();
            } else {//иначе проверяем реферии на наличие
                Query cityid = em.createNativeQuery("Select idCity From City Where(CityName=:city)");
                cityid.setParameter("city", city);
                int citid = (int) cityid.getResultList().get(0);
                Query existreferee = em.createNativeQuery("select * From Referee Where(RefereeFirstName=:fname "
                        + "AND RefereeSecondName=:sname AND RefereePatronymic=:pname AND RefereeCityId=:citid)");
                existreferee.setParameter("fname", fName);
                existreferee.setParameter("sname", sName);
                existreferee.setParameter("pname", pName);
                existreferee.setParameter("citid", citid);
                //existreferee.addEntity(Referee.class);
                List<Object> ref = existreferee.getResultList();

                if (ref.size() == 0) {
                    /*SQLQuery qw = session.createSQLQuery("Insert Into City(CityName) Values(:cityname)");
                    qw.setParameter("cityname", city);
                    qw.executeUpdate();*/
                    Query add = em.createNativeQuery("Insert Into"
                            + " Referee(Category,RefereeCityId,RefereeFirstName,"
                            + " RefereeSecondName, RefereePatronymic, FlagRef) "
                            + "Values(:categ,:refcitid,:fname,:sname,:pname,:flagref)");
                    add.setParameter("fname", fName);
                    add.setParameter("sname", sName);
                    add.setParameter("pname", pName);
                    add.setParameter("flagref", true);
                    add.setParameter("refcitid", citid);
                    add.setParameter("categ", category);
                    add.executeUpdate();
                    ResultStatus resultStatus = new ResultStatus();
                    resultStatus.setErrorCode(Constants.ERROR_CODE_OK);
                    resultStatus.setErrorText(Constants.ERROR_TEXT_OK);
                    result = new CreateRefereeResponse(fName, sName, city, resultStatus);
                    //return result;
                } else {
                    ResultStatus resultStatus = new ResultStatus();
                    resultStatus.setErrorCode(Constants.ERROR_CODE_ERROR);
                    resultStatus.setErrorText(Constants.ERROR_TEXT_ERROR);
                    result = new CreateRefereeResponse(fName, sName, city, resultStatus);
                    //return result;
                    //сообщение об ошибке
                    //WarningMessage("Такой рефери уже работатет на этом чемпионате"); 
                }
            }
        } catch (Exception e) {
           throw new RestAppException("Unable to create referee");
        }
       return result;
    }
    
    private boolean checkMatchForExistence(EntityManager em, int id ){
        Query m = em.createNativeQuery("Select * From Match where (RefereeId=:g AND HostScore is null AND GuestScore is null)");
        m.setParameter("g", id);
        return m.getResultList().isEmpty();
    }
    
    public List<String> getReferee(EntityManager em){
        Query query = em.createNamedQuery("Referee.findAll");
        List<Referee>refereeList = query.getResultList();
        List<String> res = new ArrayList<>();
        for(Referee mat: refereeList){
            String name = mat.getRefereeFirstName() +" " + mat.getRefereeSecondName();
            res.add(name);  
        }
        return res;
    }
    
    public CreateRefereeResponse excludeReferee(EntityManager em, String city, String fName, String sName, String pName, int category) throws RestAppException{
        CreateRefereeResponse result=null;
        try {
            List<String> SNP = new ArrayList<>();
            Query find = em.createNativeQuery("Select * From City Where(CityName=:cityname)");
            find.setParameter("cityname", city);
            if(!find.getResultList().isEmpty()){
                //int j = 0;
                Query f = em.createNativeQuery("Select idReferee from Referee where(RefereeFirstName=:Fname "
                        + "AND RefereeSecondName=:Sname AND RefereePatronymic=:Pname)");
                f.setParameter("Fname", fName);
                f.setParameter("Sname", sName);
                f.setParameter("Pname", pName);
                int gi = (int) f.getResultList().get(0);//получили id судьи
                
                if(!checkMatchForExistence(em, gi)){
                    Query t = em.createNativeQuery("Update `Match` set RefereeId=null where (RefereeId=:g AND HostScore is null AND GuestScore is null)");
                    t.setParameter("g", gi);
                    t.executeUpdate();
                }
                Query tq = em.createNativeQuery("Update Referee set FlagRef=false where (idReferee=:g)");
                tq.setParameter("g", gi);
                tq.executeUpdate();
                ResultStatus resultStatus = new ResultStatus();
                resultStatus.setErrorCode(Constants.ERROR_CODE_OK);
                resultStatus.setErrorText(Constants.ERROR_TEXT_OK);
                result = new CreateRefereeResponse(fName, sName, city, resultStatus);
                
            } else{
                ResultStatus resultStatus = new ResultStatus();
                resultStatus.setErrorCode(Constants.ERROR_CODE_ERROR);
                resultStatus.setErrorText(Constants.ERROR_TEXT_ERROR);
                result = new CreateRefereeResponse(fName, sName, city, resultStatus);
            }
            
        } catch (Exception e) {
            throw new RestAppException("Unable to exclude referee");
        }
        
        return result;
    }
   
}

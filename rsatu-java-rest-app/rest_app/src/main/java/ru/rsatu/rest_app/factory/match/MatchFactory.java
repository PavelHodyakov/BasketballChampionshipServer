/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.factory.match;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ru.rsatu.rest_app.Tables.Match;
import ru.rsatu.rest_app.Tables.Team;
import ru.rsatu.rest_app.constants.Constants;
import ru.rsatu.rest_app.factory.refereecreate.CreateRefereeResponse;
import ru.rsatu.rest_app.pojo.RestAppException;
import ru.rsatu.rest_app.pojo.ResultStatus;

/**
 *
 * @author pavel
 */
public class MatchFactory {
    
    public List<MatchResponse> getMatches(EntityManager em){
        Query query = em.createNamedQuery("Match.findAll");
        List<Match>matchList = query.getResultList();
        List<MatchResponse> res = new ArrayList<>();
        for(Match mat: matchList){
            res.add(new MatchResponse(mat.getTeamHostId().getTeamName(),
                  mat.getTeamGuestId().getTeamName(),
                  mat.getRefereeId().getRefereeFirstName(),
                  mat.getRefereeId().getRefereeSecondName(),
                  mat.getDatt(), mat.getHostScore(), mat.getGuestScore()));  
        }
        return res;
    }
    
    public MatchResponse createMatch(EntityManager em, String teamHost, String teamGuest, 
            String fitstNameReferee, String secondNameReferee, String date) throws RestAppException{
        MatchResponse result = null;
        try {
            Query hoid = em.createNativeQuery("select idTeam From Team Where (TeamName=:teamhost)");
            hoid.setParameter("teamhost", teamHost);
            List<Integer> l = hoid.getResultList();
            int idhost = l.get(0);//id хозяев
            Query guid = em.createNativeQuery("select idTeam From Team Where (TeamName=:teamguest)");
            guid.setParameter("teamguest", teamGuest);
            List<Integer> r = guid.getResultList();

            int idguest = r.get(0);//id гостя

            //ищем совпадающие матчи
            Query rt = em.createNativeQuery("select idMatch From Match Where (TeamHostId=:idhost AND TeamGuestId=:idguest AND Datt=:date)"); //
            rt.setParameter("idhost", idhost);
            rt.setParameter("idguest", idguest);
            rt.setParameter("date", date);
            /*("select * From Referee Where(RefereeFirstName=:fname "

                + "AND RefereeSecondName=:sname AND RefereePatronymic=:pname AND RefereeCityId=:citid)");*/
            //System.out.println("dsfvbc");
            List<Integer> have = rt.getResultList();
            if (have.size() == 0) {

                Query idref = em.createNativeQuery("Select idReferee from Referee where("
                        + "RefereeFirstName=:Fname AND RefereeSecondName=:Sname)");// AND RefereePatronymic=:Pname

                idref.setParameter("Fname", fitstNameReferee);
                idref.setParameter("Sname", secondNameReferee);
                //idref.setParameter("Pname", "");
                List<Integer> re = idref.getResultList();

                int idr = re.get(0);//id судьи

                Query ty = em.createNativeQuery("Insert into Match (TeamHostId, TeamGuestId,RefereeId,Datt, HostScore, GuestScore) Values(:hostid,:guestid,:idref,:date, 0, 0)");
                ty.setParameter("hostid", idhost);
                ty.setParameter("guestid", idguest);
                ty.setParameter("idref", idr);
                //date = new Date();
                ty.setParameter("date", date);
                ty.executeUpdate();
                ResultStatus resultStatus = new ResultStatus();
                resultStatus.setErrorCode(Constants.ERROR_CODE_OK);
                resultStatus.setErrorText(Constants.ERROR_TEXT_OK);
                //ty.executeUpdate();
                result = new MatchResponse(teamHost, teamGuest, fitstNameReferee, secondNameReferee, date, resultStatus);
                
            } else {
                //сообщение об ошибке
         //       WarningMessage("Такая игра в это время уже существует");
          ResultStatus resultStatus = new ResultStatus();
                resultStatus.setErrorCode(Constants.ERROR_CODE_ERROR);
                resultStatus.setErrorText(Constants.ERROR_TEXT_ERROR);
                result = new MatchResponse(teamHost, teamGuest, fitstNameReferee, secondNameReferee, date.toString(), resultStatus);
            }
        } catch (Exception e) {
           throw new RestAppException("Unable to create match"); 
        }
       return result;
    }
}

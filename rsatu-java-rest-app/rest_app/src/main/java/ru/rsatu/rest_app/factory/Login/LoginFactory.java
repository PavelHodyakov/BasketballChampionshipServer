/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.factory.Login;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ru.rsatu.rest_app.Tables.Role;
import ru.rsatu.rest_app.Tables.Users;
import ru.rsatu.rest_app.constants.Constants;
import ru.rsatu.rest_app.pojo.RestAppException;
import ru.rsatu.rest_app.pojo.ResultStatus;

/**
 *
 * @author pavel
 */
public class LoginFactory {
    
    public LoginResponse registryUser(EntityManager em, String login, String password) throws RestAppException{
        LoginResponse result = null;
        try {
            Query find = em.createNativeQuery("Select * From Users Where(UsersLogin=:userslogin AND UsersPassword=:userspassword)");
            find.setParameter("userslogin", login);
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            String hashPassword = Base64.getEncoder().encodeToString(bytes);
            find.setParameter("userspassword", hashPassword);
            
//            MessageDigest md = MessageDigest.getInstance("SHA-512");
//            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
//            String hashPassword = Base64.getEncoder().encodeToString(bytes);
//            Query find = em.createNativeQuery("Select * From Users Where(UsersLogin=:userslogin)");
//            find.setParameter("userslogin", login);
            //find.setParameter("userspassword", hashPassword);
            //find.addEntity(City.class);
            List<Users> ciy = find.getResultList();
            if(ciy.size() == 0){
                Query add = em.createNativeQuery("Insert Into Users(UsersLogin,UsersPassword,UsersRoleId) Values(:login,:password,1)");
                add.setParameter("login", login);
                
                add.setParameter("password", hashPassword);
                add.executeUpdate();
                result = new LoginResponse(true, "someone");
            }
        }catch (Exception e) {
           throw new RestAppException("Unable to registry user: "+ e);
        }
        return result;
    }
    
    public LoginResponse loginUser(EntityManager em, String login, String password) throws RestAppException{
        LoginResponse result = null;
        try {
            Query find = em.createNativeQuery("Select UsersRoleId From Users Where(UsersLogin=:userslogin AND UsersPassword=:userspassword)");
            find.setParameter("userslogin", login);
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            String hashPassword = Base64.getEncoder().encodeToString(bytes);
            find.setParameter("userspassword", hashPassword);
            //find.addEntity(City.class);
            List<Object> ciy = find.getResultList();
            if(ciy.size() == 1){
                Role role = em.find(Role.class, ciy.get(0));//createQuery("Select * From Role where(idRole=:idrole)");
                result = new LoginResponse(true, role.getRoleName());
            }
        } catch (Exception e) {
           throw new RestAppException("Unable to get users: " + e);
        }
       return result;
    }
}

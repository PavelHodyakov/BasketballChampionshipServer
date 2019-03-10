/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.Tables;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pavel
 */
@Entity
@Table(name = "Users")
@XmlRootElement
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idUsers")
    private Integer idUsers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "UsersLogin")
    private String usersLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "UsersPassword")
    private String usersPassword;
    @JoinColumn(name = "UsersRoleId", referencedColumnName = "idRole")
    @ManyToOne(optional = false)
    private Role usersRoleId;

    public Users(Integer idUsers) {
        this.idUsers = idUsers;
    }

    public Users() {
    }

    public Users(Integer idUsers, String usersLogin, String usersPassword, Role usersRoleId) {
        this.idUsers = idUsers;
        this.usersLogin = usersLogin;
        this.usersPassword = usersPassword;
        this.usersRoleId = usersRoleId;
    }

    public Integer getIdUsers() {
        return idUsers;
    }

    public String getUsersLogin() {
        return usersLogin;
    }

    public String getUsersPassword() {
        return usersPassword;
    }

    public Role getUsersRoleId() {
        return usersRoleId;
    }

    public void setIdUsers(Integer idUsers) {
        this.idUsers = idUsers;
    }

    public void setUsersLogin(String usersLogin) {
        this.usersLogin = usersLogin;
    }

    public void setUsersPassword(String usersPassword) {
        this.usersPassword = usersPassword;
    }

    public void setUsersRoleId(Role usersRoleId) {
        this.usersRoleId = usersRoleId;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsers != null ? idUsers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.idUsers == null && other.idUsers != null) || (this.idUsers != null && !this.idUsers.equals(other.idUsers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.Tables.Users[ idUsers=" + idUsers + " ]";
    }
    
}

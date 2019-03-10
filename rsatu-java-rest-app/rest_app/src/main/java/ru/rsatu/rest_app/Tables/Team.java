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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pavel
 */
@Entity
@Table(name = "Team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t"),
    @NamedQuery(name = "Team.findByIdTeam", query = "SELECT t FROM Team t WHERE t.idTeam = :idTeam"),
    @NamedQuery(name = "Team.findByTeamName", query = "SELECT t FROM Team t WHERE t.teamName = :teamName"),
    @NamedQuery(name = "Team.findByFlag", query = "SELECT t FROM Team t WHERE t.flag = :flag")})
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idTeam")
    private Integer idTeam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TeamName")
    private String teamName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Flag")
    private boolean flag;
    @JoinColumn(name = "TeamCityId", referencedColumnName = "idCity")
    @ManyToOne(optional = false)
    private City teamCityId;
    @OneToMany(mappedBy = "teamGuestId")
    private Collection<Match> matchCollection;
    @OneToMany(mappedBy = "teamHostId")
    private Collection<Match> matchCollection1;

    public Team() {
    }

    public Team(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public Team(Integer idTeam, String teamName, boolean flag) {
        this.idTeam = idTeam;
        this.teamName = teamName;
        this.flag = flag;
    }

    public Integer getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public City getTeamCityId() {
        return teamCityId;
    }

    public void setTeamCityId(City teamCityId) {
        this.teamCityId = teamCityId;
    }

    @XmlTransient
    public Collection<Match> getMatchCollection() {
        return matchCollection;
    }

    public void setMatchCollection(Collection<Match> matchCollection) {
        this.matchCollection = matchCollection;
    }

    @XmlTransient
    public Collection<Match> getMatchCollection1() {
        return matchCollection1;
    }

    public void setMatchCollection1(Collection<Match> matchCollection1) {
        this.matchCollection1 = matchCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTeam != null ? idTeam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Team)) {
            return false;
        }
        Team other = (Team) object;
        if ((this.idTeam == null && other.idTeam != null) || (this.idTeam != null && !this.idTeam.equals(other.idTeam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.Tables.Team[ idTeam=" + idTeam + " ]";
    }
    
}

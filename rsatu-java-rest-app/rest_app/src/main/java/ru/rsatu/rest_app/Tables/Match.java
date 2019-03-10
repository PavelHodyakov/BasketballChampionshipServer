/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest_app.Tables;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pavel
 */
@Entity
@Table(name = "Match")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Match.findAll", query = "SELECT m FROM Match m"),
    @NamedQuery(name = "Match.findByIdMatch", query = "SELECT m FROM Match m WHERE m.idMatch = :idMatch"),
    @NamedQuery(name = "Match.findByHostScore", query = "SELECT m FROM Match m WHERE m.hostScore = :hostScore"),
    @NamedQuery(name = "Match.findByGuestScore", query = "SELECT m FROM Match m WHERE m.guestScore = :guestScore"),
    @NamedQuery(name = "Match.findByDatt", query = "SELECT m FROM Match m WHERE m.datt = :datt")})
public class Match implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    /*com.mycompany_BasketballChampion_jar_1.0-SNAPSHOTPU*/
    @Column(name = "idMatch")
    private Integer idMatch;
    @Column(name = "HostScore")
    private Integer hostScore;
    @Column(name = "GuestScore")
    private Integer guestScore;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Datt")
    private String datt;
    @JoinColumn(name = "RefereeId", referencedColumnName = "idReferee")
    @ManyToOne
    private Referee refereeId;
    @JoinColumn(name = "TeamGuestId", referencedColumnName = "idTeam")
    @ManyToOne
    private Team teamGuestId;
    @JoinColumn(name = "TeamHostId", referencedColumnName = "idTeam")
    @ManyToOne
    private Team teamHostId;

    public Match() {
    }

    public Match(Integer idMatch) {
        this.idMatch = idMatch;
    }

    public Match(Integer idMatch, String datt) {
        this.idMatch = idMatch;
        this.datt = datt;
    }

    public Integer getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Integer idMatch) {
        this.idMatch = idMatch;
    }

    public Integer getHostScore() {
        return hostScore;
    }

    public void setHostScore(Integer hostScore) {
        this.hostScore = hostScore;
    }

    public Integer getGuestScore() {
        return guestScore;
    }

    public void setGuestScore(Integer guestScore) {
        this.guestScore = guestScore;
    }

    public String getDatt() {
        return datt;
    }

    public void setDatt(String datt) {
        this.datt = datt;
    }

    public Referee getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(Referee refereeId) {
        this.refereeId = refereeId;
    }

    public Team getTeamGuestId() {
        return teamGuestId;
    }

    public void setTeamGuestId(Team teamGuestId) {
        this.teamGuestId = teamGuestId;
    }

    public Team getTeamHostId() {
        return teamHostId;
    }

    public void setTeamHostId(Team teamHostId) {
        this.teamHostId = teamHostId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatch != null ? idMatch.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Match)) {
            return false;
        }
        Match other = (Match) object;
        if ((this.idMatch == null && other.idMatch != null) || (this.idMatch != null && !this.idMatch.equals(other.idMatch))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.Tables.Match[ idMatch=" + idMatch + " ]";
    }
    
}

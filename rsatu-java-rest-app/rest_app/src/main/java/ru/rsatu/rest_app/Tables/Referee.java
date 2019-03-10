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
@Table(name = "Referee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Referee.findAll", query = "SELECT r FROM Referee r"),
    @NamedQuery(name = "Referee.findByIdReferee", query = "SELECT r FROM Referee r WHERE r.idReferee = :idReferee"),
    @NamedQuery(name = "Referee.findByRefereeFirstName", query = "SELECT r FROM Referee r WHERE r.refereeFirstName = :refereeFirstName"),
    @NamedQuery(name = "Referee.findByRefereeSecondName", query = "SELECT r FROM Referee r WHERE r.refereeSecondName = :refereeSecondName"),
    @NamedQuery(name = "Referee.findByRefereePatronymic", query = "SELECT r FROM Referee r WHERE r.refereePatronymic = :refereePatronymic"),
    @NamedQuery(name = "Referee.findByCategory", query = "SELECT r FROM Referee r WHERE r.category = :category"),
    @NamedQuery(name = "Referee.findByFlagRef", query = "SELECT r FROM Referee r WHERE r.flagRef = :flagRef")})
public class Referee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idReferee")
    private Integer idReferee;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "RefereeFirstName")
    private String refereeFirstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "RefereeSecondName")
    private String refereeSecondName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "RefereePatronymic")
    private String refereePatronymic;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Category")
    private int category;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FlagRef")
    private boolean flagRef;
    @JoinColumn(name = "RefereeCityId", referencedColumnName = "idCity")
    @ManyToOne(optional = false)
    private City refereeCityId;
    @OneToMany(mappedBy = "refereeId")
    private Collection<Match> matchCollection;

    public Referee() {
    }

    public Referee(Integer idReferee) {
        this.idReferee = idReferee;
    }

    public Referee(Integer idReferee, String refereeFirstName, String refereeSecondName, String refereePatronymic, int category, boolean flagRef) {
        this.idReferee = idReferee;
        this.refereeFirstName = refereeFirstName;
        this.refereeSecondName = refereeSecondName;
        this.refereePatronymic = refereePatronymic;
        this.category = category;
        this.flagRef = flagRef;
    }

    public Integer getIdReferee() {
        return idReferee;
    }

    public void setIdReferee(Integer idReferee) {
        this.idReferee = idReferee;
    }

    public String getRefereeFirstName() {
        return refereeFirstName;
    }

    public void setRefereeFirstName(String refereeFirstName) {
        this.refereeFirstName = refereeFirstName;
    }

    public String getRefereeSecondName() {
        return refereeSecondName;
    }

    public void setRefereeSecondName(String refereeSecondName) {
        this.refereeSecondName = refereeSecondName;
    }

    public String getRefereePatronymic() {
        return refereePatronymic;
    }

    public void setRefereePatronymic(String refereePatronymic) {
        this.refereePatronymic = refereePatronymic;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean getFlagRef() {
        return flagRef;
    }

    public void setFlagRef(boolean flagRef) {
        this.flagRef = flagRef;
    }

    public City getRefereeCityId() {
        return refereeCityId;
    }

    public void setRefereeCityId(City refereeCityId) {
        this.refereeCityId = refereeCityId;
    }

    @XmlTransient
    public Collection<Match> getMatchCollection() {
        return matchCollection;
    }

    public void setMatchCollection(Collection<Match> matchCollection) {
        this.matchCollection = matchCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReferee != null ? idReferee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Referee)) {
            return false;
        }
        Referee other = (Referee) object;
        if ((this.idReferee == null && other.idReferee != null) || (this.idReferee != null && !this.idReferee.equals(other.idReferee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.Tables.Referee[ idReferee=" + idReferee + " ]";
    }
    
}

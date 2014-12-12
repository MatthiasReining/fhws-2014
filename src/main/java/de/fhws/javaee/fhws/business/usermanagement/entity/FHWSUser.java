/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.business.usermanagement.entity;

import de.fhws.javaee.fhws.business.usermanagement.controller.PWService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
    @NamedQuery(
            name = FHWSUser.FIND_BY_EMAIL,
            query = "SELECT u FROM FHWSUser u WHERE u.email = :" + FHWSUser.PARAM_EMAIL
    ),
    @NamedQuery(
            name = FHWSUser.FIND_ALL,
            query = "SELECT u FROM FHWSUser u"
    )})
public class FHWSUser implements Serializable {

    public static final String FIND_BY_EMAIL = "User.FinyByEMail";
    public static final String FIND_ALL = "User.findAll";
    public static final String PARAM_EMAIL = "email";

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @Size(min = 2, max = 240)
    private String email;
    private String password;

    private String firstname;
    private String lastname;
    private String street;
    private String housenumber;

    @OneToMany(mappedBy = "fhwsUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LoginStatistic> loginStatistics;

    @Size(max = 10)
    private String zip;
    private String city;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastLogin;

    public FHWSUser() {
    }

    public FHWSUser(String email, String password, Date lastLogin) {
        this.email = email;
        this.password = password;
        this.lastLogin = lastLogin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<LoginStatistic> getLoginStatistics() {
        return loginStatistics;
    }

    public void setLoginStatistics(List<LoginStatistic> loginStatistics) {
        this.loginStatistics = loginStatistics;
    }

}

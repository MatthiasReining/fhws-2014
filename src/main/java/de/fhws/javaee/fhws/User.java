/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import java.io.Serializable;
import javax.validation.constraints.Size;

/**
 *
 * @author Matthias Reining
 */
public class User implements Serializable {

    @Size(min = 2, max = 240)
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String street;
    private String housenumber;
    private String zip;
    private String city;
    private String lastLogin;
    private Long id;

    public User() {
    }

    public User(String email, String password, String lastLogin) {
        this.email = email;
        this.password = password;
        this.lastLogin = lastLogin;
    }
    
    public boolean checkPassword(String testPassword) {
        if (testPassword == null) return false;
        return (testPassword.equals(this.password));
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

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
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

}

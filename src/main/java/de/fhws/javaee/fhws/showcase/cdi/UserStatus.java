/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.showcase.cdi;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Matthias Reining
 */
@Named
@SessionScoped
public class UserStatus implements Serializable {

    private String status;
    
    @PostConstruct
    public void init() {
        status = "neu";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

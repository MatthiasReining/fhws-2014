/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.business.usermanagement.controller;

import de.fhws.javaee.fhws.business.usermanagement.entity.FHWSLoginEvent;
import de.fhws.javaee.fhws.business.loginfo.entity.LogInfo;
import java.util.Date;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

/**
 *
 * @author Matthias Reining
 */
@Stateless
public class LoginWriter {
    @Inject
    EntityManager em;

    public void write(@Observes FHWSLoginEvent loginEvent) {

        LogInfo li = new LogInfo();
        li.setCreated(new Date());
        li.setMessage(loginEvent.getUser().getEmail() + " hat sich angemeldet");
        
        em.persist(li);
    }
}

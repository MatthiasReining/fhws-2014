/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.showcase.cdi;

import de.fhws.javaee.fhws.business.loginfo.entity.LogInfo;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named
public class StatelesSessionBeanTest {
    
    @PersistenceContext
    EntityManager em;

    public String getMessage() {
        LogInfo logInfo = new LogInfo();
        logInfo.setCreated(new Date());
        logInfo.setMessage("in SLSBTest" + new Date());
        em.persist(logInfo);       

        return logInfo.getMessage();
    }
}

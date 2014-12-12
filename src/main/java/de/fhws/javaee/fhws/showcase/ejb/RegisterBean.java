/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.showcase.ejb;

import de.fhws.javaee.fhws.showcase.jpa.LogInfo;
import de.fhws.javaee.fhws.business.usermanagement.entity.FHWSUser;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Matthias Reining
 */
@Stateless
@Remote(RegisterBeanRemote.class)
@Local(RegisterBeanLocal.class)
public class RegisterBean implements RegisterBeanLocal, RegisterBeanRemote {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public String persistLocal() {
        return persist();
    }

    @Override
    public void persistRemote() {
        persist();
    }
    
    public void irgendwas() {
        
    }
    
    String persist() {
        
        LogInfo li = new LogInfo();
        li.setCreated(new Date());
        li.setMessage("in Persist vom Business Object");
        
        em.persist(li);
        
        FHWSUser user = em.find(FHWSUser.class, 17l);
        user.setHousenumber("234");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RegisterBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        user.setHousenumber("456");
        
        System.out.println("komplexe Businesslogik");
        return "komplexes Ergebnis";
    }

}

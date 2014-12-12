/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.showcase.ejb;

import de.fhws.javaee.fhws.showcase.jpa.LogInfo;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ComplexService2 {
    
    
    @PersistenceContext
    EntityManager em;

    @Asynchronous
    public void calculate(int input) {
        System.out.println("Start ComplexService2#calculate");
        LogInfo li = new LogInfo();
        li.setCreated(new Date());
        li.setMessage("in ComplexService2#calculate");

        em.persist(li);
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ComplexService2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Ende ComplexService2");
        
        
        //return input*50;
    }
}

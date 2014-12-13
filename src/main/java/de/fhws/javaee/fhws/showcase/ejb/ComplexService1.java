/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.showcase.ejb;

import de.fhws.javaee.fhws.business.loginfo.entity.LogInfo;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ComplexService1 {

    @PersistenceContext
    EntityManager em;

    @EJB
    ComplexService2 cl2;


    public int calculate(int input) {
        System.out.println("Start ComplexService1#calculate");
        LogInfo li = new LogInfo();
        li.setCreated(new Date());
        li.setMessage("in ComplexService1#calculate");

        cl2.calculate(input);
        
        

        em.persist(li);

        //  input = input / 0;
        System.out.println("Ende ComplexService1");
        return input * 5;
    }

}

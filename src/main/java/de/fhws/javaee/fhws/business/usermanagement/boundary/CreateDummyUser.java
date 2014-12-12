/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.business.usermanagement.boundary;

import de.fhws.javaee.fhws.business.usermanagement.controller.PWService;
import de.fhws.javaee.fhws.business.usermanagement.entity.FHWSUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Matthias Reining
 */
@WebServlet(name = "CreateDummyUser", urlPatterns = {"/CreateDummyUser"})
public class CreateDummyUser extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    
    @Resource
    UserTransaction ut;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ut.begin();
            em.persist(new FHWSUser("max.mustermann@fhws.de", new PWService().createPWHash("max"), new Date()));
            em.persist(new FHWSUser("donald.duck@fhws.de", new PWService().createPWHash("donald"), new Date()));
            em.persist(new FHWSUser("mickey.mouse@fhws.de", new PWService().createPWHash("mickey"), new Date()));
            ut.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(CreateDummyUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

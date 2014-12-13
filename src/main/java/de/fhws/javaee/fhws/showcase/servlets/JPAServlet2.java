/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.showcase.servlets;

import de.fhws.javaee.fhws.business.loginfo.entity.LogInfo;
import java.io.IOException;
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
@WebServlet("/JPAServlet2")
public class JPAServlet2 extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction ut;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogInfo logInfo = null;
        String newMessage = req.getParameter("newMessage");
        if (newMessage != null) {
            try {
                logInfo = new LogInfo();
                logInfo.setCreated(new Date());
                logInfo.setMessage("Im Sevlet JPAServlet");
                ut.begin();
                em.merge(logInfo);
                
                
                ut.commit();
                
                resp.getWriter().println("LogInfo: " + logInfo.getId() + ": " + logInfo.getCreated() + " - " + logInfo.getMessage());
                return;
            } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
                Logger.getLogger(JPAServlet2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Long id = new Long(req.getParameter("id"));
            logInfo = em.find(LogInfo.class, id);
        }

        resp.getWriter().println("LogInfo: " + logInfo.getId() + ": " + logInfo.getCreated() + " - " + logInfo.getMessage());
    }

}

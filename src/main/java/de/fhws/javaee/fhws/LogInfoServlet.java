/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
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

@WebServlet("/LogInfoServlet")
public class LogInfoServlet extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction ut;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idText = req.getParameter("id");
        String queryMessage = req.getParameter("queryMessage");
        Long id = null;
        if (idText != null)
            id = new Long(idText);

        String newMessage = req.getParameter("newMessage");

        LogInfo logInfo = null;
        if (queryMessage != null) {
            //logInfo = em.createNamedQuery(LogInfo.QUERY_BY_MESSAGE, LogInfo.class)
            //        .setParameter(LogInfo.PARAM_MESSAGE, queryMessage)
            //        .getSingleResult();

            List<LogInfo> resultList = em.createNamedQuery(LogInfo.QUERY_BY_MESSAGE, LogInfo.class)
                    .setParameter(LogInfo.PARAM_MESSAGE, queryMessage)
                    .setMaxResults(10)
                    .setFirstResult(0)
                    .getResultList();

            resp.getWriter().println("-- liste --");
            for (LogInfo li : resultList) {
                resp.getWriter()
                        .println(li.getId() + " - " + li.getCreated() + " - " + li.getMessage());
            }
            resp.getWriter().println("--------");

            logInfo = em.createQuery(
                    "SELECT li FROM LogInfo li WHERE li.message = :message", LogInfo.class)
                    .setParameter("message", "alles anders")
                    .setMaxResults(10)
                    .getSingleResult();

        } else if (newMessage
                != null) {
            rollbackCase(false);
            logInfo = createNewEntry(logInfo, newMessage);

        } else {
            logInfo = em.find(LogInfo.class, id);
        }

        resp.getWriter()
                .println(logInfo.getId() + " - " + logInfo.getCreated() + " - " + logInfo.getMessage());

    }

    protected LogInfo createNewEntry(LogInfo logInfo, String newMessage) {
        try {
            ut.begin();
            logInfo = new LogInfo();
            logInfo.setCreated(new Date());
            logInfo.setMessage(newMessage);

            em.persist(logInfo);

            logInfo.setMessage(newMessage + "...");

            ut.commit();

            logInfo.setMessage(newMessage + "---");
            ut.begin();
            logInfo = em.merge(logInfo);

        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(LogInfoServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return logInfo;
    }

    void rollbackCase(boolean allesOk) {

        try {
            ut.begin();

            LogInfo logInfo = em.find(LogInfo.class, 1L);
            logInfo.setMessage(
                    "alles XXXXXXXXXXXXX");

            if (!allesOk) {

                ut.rollback();
                return;
            }

            ut.commit();

        } catch (SecurityException | IllegalStateException | SystemException | NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException ex) {
            Logger.getLogger(LogInfoServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

}

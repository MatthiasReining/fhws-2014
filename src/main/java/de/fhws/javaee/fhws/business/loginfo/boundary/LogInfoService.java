/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.business.loginfo.boundary;

import de.fhws.javaee.fhws.business.loginfo.entity.LogInfo;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@WebService
public class LogInfoService {

    @PersistenceContext
    EntityManager em;

    public List<LogInfo> getAll() {
        return em.createNamedQuery(LogInfo.FIND_ALL, LogInfo.class).getResultList();
    }

    public LogInfo getByMessage(@WebParam(name="message") String message) {
        System.out.println(message);
        return em.createNamedQuery(LogInfo.QUERY_BY_MESSAGE, LogInfo.class)
                .setParameter(LogInfo.PARAM_MESSAGE, message)
                .getSingleResult();
    }
}

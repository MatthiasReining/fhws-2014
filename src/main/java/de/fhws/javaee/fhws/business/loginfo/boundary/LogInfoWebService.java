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

@Stateless
@WebService(name = "loginfo")
public class LogInfoWebService {

    @Inject
    LogInfoService logInfoService;

    public List<LogInfo> getAll() {
        return logInfoService.getAll();
    }

    public LogInfo getByMessage(@WebParam(name = "message") String message) {
        System.out.println(message);
        return logInfoService.getByMessage(message);
    }

}

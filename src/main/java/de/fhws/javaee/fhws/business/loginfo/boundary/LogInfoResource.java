package de.fhws.javaee.fhws.business.loginfo.boundary;

import de.fhws.javaee.fhws.business.loginfo.entity.LogInfo;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/loginfo")
public class LogInfoResource {

    @Inject
    LogInfoService logInfoService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LogInfo> getAll() {
        return logInfoService.getAll();
    }
}

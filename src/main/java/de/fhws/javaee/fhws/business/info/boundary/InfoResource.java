/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.business.info.boundary;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("info")
@Produces(MediaType.APPLICATION_JSON)
public class InfoResource {

    @GET
    public Response getInfo() {
        JsonObject object = Json.createObjectBuilder()
                .add("version", "1.2.3")
                .add("developer", "JavaEE Team @ FHWS")
                .build();
        
        return Response.ok(object).build();
    }
}

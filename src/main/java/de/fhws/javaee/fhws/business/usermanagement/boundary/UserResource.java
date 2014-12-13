/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.business.usermanagement.boundary;

import de.fhws.javaee.fhws.business.loginfo.entity.LogInfo;
import de.fhws.javaee.fhws.business.usermanagement.entity.FHWSUser;
import de.fhws.javaee.fhws.business.usermanagement.entity.LoginStatistic;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    public List<FHWSUser> getAll(@QueryParam("firstname") String firstname) {
        List<FHWSUser> allUsers = userService.getAllFHWSUsers();
        List<FHWSUser> result = allUsers;

        if (firstname != null) {
            result = new ArrayList<>();
            for (FHWSUser user : allUsers) {
                if (firstname.equals(user.getFirstname()))
                    result.add(user);
            }
        }
        return result;
    }

    @GET
    @Path("{id}")
    public FHWSUser getUserById(@PathParam("id") Long id) {
        return userService.findUserById(id);
    }

    @GET
    @Path("{id}/statistics")
    public Response getLoginStatisticByUserId(@PathParam("id") Long id) {
        try {
            List<LoginStatistic> result = userService.findUserById(id).getLoginStatistics();
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    public Response updateUser(FHWSUser user) {
        FHWSUser updatedUser = userService.updateUser(user);

        return Response.status(201).entity(updatedUser).build();
    }

    @GET
    @Path("list")
    public Response getAll4List() {
        List<FHWSUser> users = userService.getAllFHWSUsers();
        JsonArrayBuilder result = Json.createArrayBuilder();
        for (FHWSUser user : users) {
            result.add(Json.createObjectBuilder()
                    .add("email", user.getEmail())
                    .add("firstname", user.getFirstname())
                    .add("lastname", user.getLastname())
            );
        }
        return Response.status(Response.Status.OK)
                .entity(result.build()).build();

    }

    @GET
    @Path("v2")
    public Response getAll2() {

        return Response.status(Response.Status.FOUND)
                .entity(userService.getAllFHWSUsers())
                .build();
    }

}

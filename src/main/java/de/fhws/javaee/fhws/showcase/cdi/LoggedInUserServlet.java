/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.showcase.cdi;

import de.fhws.javaee.fhws.business.usermanagement.entity.FHWSUser;
import de.fhws.javaee.fhws.business.usermanagement.entity.LoggedInUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matthias Reining
 */
@WebServlet("/LoggedInUserServlet")
public class LoggedInUserServlet extends HttpServlet {

    @Inject
    @LoggedInUser
    FHWSUser loggedInUser;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getOutputStream().println(loggedInUser.getEmail());

    }

}

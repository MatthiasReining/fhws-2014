/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.showcase.servlets;

import de.fhws.javaee.fhws.business.usermanagement.entity.FHWSUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Matthias Reining
 */
@WebServlet(name = "SessionTestServlet", urlPatterns = {"/SessionTestServlet"})
public class SessionTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession(true);
        FHWSUser sessionUser = (FHWSUser) session.getAttribute("user");
        
        System.out.println("user: " + sessionUser.getEmail());
        resp.getOutputStream().println("Hallo: " + sessionUser.getEmail());
    }

  
}

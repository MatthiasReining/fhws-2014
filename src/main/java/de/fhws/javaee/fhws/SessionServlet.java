/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

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
@WebServlet(name = "SessionServlet", urlPatterns = {"/SessionServlet"})
public class SessionServlet extends HttpServlet {

    int counter;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        String userText = req.getParameter("email");
        FHWSUser user = new FHWSUser(userText, null, null);
        
        counter++;
        
        System.out.println("...");
        
        session.setAttribute("user", user);
        
        FHWSUser sessionUser = (FHWSUser) session.getAttribute("user");
        
        System.out.println("user: " + sessionUser.getEmail());
        resp.getOutputStream().println("bin da..." + sessionUser.getEmail() + "     counter: " + counter);
    }

}

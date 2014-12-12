/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.showcase.cdi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
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
@WebServlet("/CDITestServlet")
public class CDITestServlet extends HttpServlet {

    @Inject @Special
    Message message;
    
    @Inject
    Message messageDefault;
    
    @Inject BusinessLogic bl;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        bl.run();
        System.out.println("...");
        
        System.out.println(message.getText());
        System.out.println(messageDefault.getText());
        
        resp.getOutputStream().println(message.getText());
        resp.getOutputStream().println(messageDefault.getText());

    }

}

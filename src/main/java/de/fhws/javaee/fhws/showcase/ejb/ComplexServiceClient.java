/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.showcase.ejb;

import de.fhws.javaee.fhws.showcase.ejb.ComplexService1;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ComplexServiceClient")
public class ComplexServiceClient extends HttpServlet {

    @EJB
    ComplexService1 cl1;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int result = cl1.calculate(5);
        
        resp.getWriter().println("Result: "+ result);
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterClientServlet")
public class RegisterClientServlet extends HttpServlet {

    @EJB
    RegisterBeanLocal register;
    
    @EJB
    ComplexService1 cls;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String result = register.persistLocal();
        
        resp.getWriter().println(cls.calculate(20));
        
        resp.getWriter().println(result);
        resp.getWriter().println("OK");
    }

}

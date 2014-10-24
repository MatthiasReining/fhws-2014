/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Matthias Reining
 */
@WebServlet("/DBServlet")
public class DBServlet extends HttpServlet {

    //@Resource(name = "FHWS-DS", lookup = "java:jboss/datasources/FHWS-DS")
    //DataSource ds;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            DataSource ds = (DataSource) InitialContext.doLookup("java:jboss/datasources/FHWS-DS");

            Connection connection = ds.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TEST");
            while (rs.next()) {
                resp.getWriter().println(rs.getString(1) + " - " + rs.getString(2));
            }

            connection.close();

        } catch (SQLException ex) {

            Logger.getLogger(DBServlet.class.getName()).log(Level.SEVERE, null, ex);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DBServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

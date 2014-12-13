/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.business.loginfo.boundary;

import de.fhws.javaee.fhws.business.loginfo.entity.LogInfo;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginfoTestServlet")
public class LogInfoTestServlet extends HttpServlet {

    //@Inject
    //LogInfoService lis;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      /*  List<LogInfo> list = lis.getAll();

        for (LogInfo li : list) {
            resp.getOutputStream().println(li.getId() + " - " + li.getMessage() + "(" + li.getCreated() + ")");
        }
*/
    }

}

package de.fhws.javaee.fhws;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration-servlet"})
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String email = request.getParameter("email");
        String pw = request.getParameter("password");
        
        if ("admin".equalsIgnoreCase(email)) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;
        }
        
        System.out.println("req with " + email + "/" + pw);
                
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistrationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Hallo " + email + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    
}

package de.fhws.javaee.fhws;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
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
        
        request.setAttribute("value", "blub blub");
        
        response.getWriter().println("<hr>aus Registration Servlet<br>" );
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/TestServlet");
        dispatcher.include(request, response);
        
        response.getWriter().println("<hr>Ende aus Registration Servlet<br>" );
        
    }
    
    
}

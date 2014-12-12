package de.fhws.javaee.fhws.showcase.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet  {
    
    public TestServlet() {
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Es ist " + new Date() + "</h1>");
            out.println("<h1>Es ist " + request.getAttribute("value") + "</h1>");
            out.println("Der Faktor" + calculate(120));
            out.println("</body>");
            out.println("</html>");

        }

    }

    int calculate(int factor) {
        double internal = 1.0;
        if (factor < 100)
            internal = 0.8;

        if (factor >= 100 && factor < 200) {
            if (factor > 150)
                internal = 0.95;
            else
                internal = 0.9;
        }

        return new Double(factor * internal).intValue();
    }
}

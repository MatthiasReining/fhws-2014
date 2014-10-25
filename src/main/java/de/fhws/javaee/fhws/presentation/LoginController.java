/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.presentation;

import de.fhws.javaee.fhws.business.usermanagement.entity.FHWSUser;
import de.fhws.javaee.fhws.business.usermanagement.boundary.UserService;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    UserService userService;

    private String email;
    private String password;

    private FHWSUser user;

    private String dummyValue;

    public LoginController() {
        System.out.println("im LoginController Constructor");
    }

    public FHWSUser getUser() {
        return user;
    }

    public void emailChanged(ValueChangeEvent e) {

        dummyValue = "Das Feld email wurde geändert (alter Wert: " + e.getOldValue() + "; neuer Wert: " + e.getNewValue() + ") um " + new Date();
    }

    public String login() {

        user = userService.loginUser(email, password, getIPAddress());

        if (user != null)
            return "user-list.xhtml?faces-redirect=true";

        //Login fehlgeschlagen, Hinweis Meldung ausgeben
        Locale loc = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        System.out.println("Locale: " + loc);
        ResourceBundle bundle = ResourceBundle.getBundle("messages", loc);
        String msg = bundle.getString("login_failed");
        //String msg = "login failed";

        FacesMessage m = new FacesMessage(msg);
        FacesContext.getCurrentInstance().addMessage("loginMessage", m);

        return "login.xhtml";

    }

    String getIPAddress() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    public String getDummyValue() {
        return dummyValue;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

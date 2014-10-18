/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    User user = new User();

    private String dummyValue;

    public LoginController() {
        System.out.println("im LoginController Constructor");
    }

    public User getUser() {
        return user;
    }

    public void emailChanged() {

        dummyValue = "Das Feld email wurde geändert (alter Wert: " + user.getEmail() + " ) um " + new Date();
    }

    public String login() {
        System.out.println("email: " + user.getEmail());

        if (user.getEmail().equals(user.getPassword())) {
            return "user-list.xhtml?faces-redirect=true";
        } else {

            Locale loc = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
            System.out.println("Locale: " + loc);
            ResourceBundle bundle = ResourceBundle.getBundle("messages", loc);
            String msg = bundle.getString("login_failed");
            //String msg = "login failed";

            FacesMessage m = new FacesMessage(msg);
            FacesContext.getCurrentInstance().addMessage("loginMessage", m);

            return "login.xhtml";

        }
    }

    public String getDummyValue() {
        return dummyValue;
    }
}

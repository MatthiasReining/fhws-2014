/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import java.io.Serializable;
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

    public LoginController() {
        System.out.println("im LoginController Constructor");
    }

    public User getUser() {
        return user;
    }

    public String login() {
        System.out.println("email: " + user.getEmail());

        if (user.getEmail().equals(user.getPassword())) {
            return "user-list.xhtml?faces-redirect=true";
        } else {

            //Locale loc = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            //ResourceBundle bundle = ResourceBundle.getBundle(
            //        FacesContext.getCurrentInstance().getApplication().getMessageBundle(), loc);
            //String msg = bundle.getString("login_failed");
            String msg = "login failed";

            FacesMessage m = new FacesMessage(msg);
            FacesContext.getCurrentInstance().addMessage("loginMessage", m);

            return "login.xhtml";

        }
    }

}

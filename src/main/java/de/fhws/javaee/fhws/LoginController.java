/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {
    
    @PersistenceContext
    EntityManager em;

    private String email;
    private String password;
    
    private User user;

    private String dummyValue;

    public LoginController() {
        System.out.println("im LoginController Constructor");
    }

    public User getUser() {
        return user;
    }

    public void emailChanged() {

        dummyValue = "Das Feld email wurde geändert (alter Wert: " + email + " ) um " + new Date();
    }
    
    public String login() {
        
        List<User> users = em.createNamedQuery(User.FIND_BY_EMAIL, User.class)
                .setParameter(User.PARAM_EMAIL, email)
                .getResultList();
        
        User testUser = null;
        if (users.size() > 0)
            testUser = users.get(0);
        
        
        if (testUser != null && testUser.checkPassword(password)) {
            user = testUser;
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

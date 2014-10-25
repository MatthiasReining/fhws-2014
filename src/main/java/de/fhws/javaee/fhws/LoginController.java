/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction ut;

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

    public String login() throws NotSupportedException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
        ut.begin();
        List<FHWSUser> users = em.createNamedQuery(FHWSUser.FIND_BY_EMAIL, FHWSUser.class)
                .setParameter(FHWSUser.PARAM_EMAIL, email)
                .getResultList();

        FHWSUser testUser = null;
        if (users.size() > 0)
            testUser = users.get(0);

        if (testUser != null && testUser.checkPassword(password)) {
            user = testUser;

            LoginStatistic ls = new LoginStatistic();
            ls.setFhwsUser(user);
            ls.setIpAddress(getIPAddress());

            if (user.getLoginStatistics() == null)
                user.setLoginStatistics(new ArrayList<LoginStatistic>());
            user.getLoginStatistics().add(ls);

            ut.commit();
            return "user-list.xhtml?faces-redirect=true";
        } else {

            Locale loc = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
            System.out.println("Locale: " + loc);
            ResourceBundle bundle = ResourceBundle.getBundle("messages", loc);
            String msg = bundle.getString("login_failed");
            //String msg = "login failed";

            FacesMessage m = new FacesMessage(msg);
            FacesContext.getCurrentInstance().addMessage("loginMessage", m);

            ut.commit();

            return "login.xhtml";

        }
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

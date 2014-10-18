/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Matthias Reining
 */
@ManagedBean
@SessionScoped
public class EditUserController {

    private User user;

    public String edit(User user) {
        this.user = user;
        return "edit-user?faces-redirect=true";
    }
    
    public String save() {
        System.out.println( user.getEmail());
        Database.getInstance().updateUser(user);
        return "user-list?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

}

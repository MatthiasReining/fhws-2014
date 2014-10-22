/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Matthias Reining
 */
@ManagedBean
@SessionScoped
public class EditUserController {

    private User user;

    public String edit(long id) {
        this.user = Database.getInstance().getUserById(id);
        return "edit-user?faces-redirect=true";
    }

    public String newUser() {
        this.user = new User();
        return "edit-user?faces-redirect=true";
    }

    public String save() {
        System.out.println(user.getEmail());
        System.out.println(user.getId());
        if (user.getId() == null)
            Database.getInstance().persistNewUser(user);
        else
            Database.getInstance().updateUser(user);
        return "user-list?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

}

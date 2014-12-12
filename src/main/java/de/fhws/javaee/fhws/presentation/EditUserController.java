/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.presentation;

import de.fhws.javaee.fhws.business.usermanagement.entity.FHWSUser;
import de.fhws.javaee.fhws.business.usermanagement.boundary.UserService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 *
 * @author Matthias Reining
 */
@Named
@SessionScoped
public class EditUserController implements Serializable {

    @Inject
    UserService userService;

    private FHWSUser user;

    public String edit(long id) throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException, NotSupportedException {
        user = userService.findUserById(id);
        return "edit-user?faces-redirect=true";
    }

    public String newUser() {
        this.user = new FHWSUser();
        return "edit-user?faces-redirect=true";
    }

    public String save() {
        userService.updateUser(user);
        return "user-list?faces-redirect=true";

    }

    public FHWSUser getUser() {
        return user;
    }

}

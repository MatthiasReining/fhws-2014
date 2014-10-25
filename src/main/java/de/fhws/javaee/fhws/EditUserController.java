/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Matthias Reining
 */
@ManagedBean
@SessionScoped
public class EditUserController {
    
    @PersistenceContext
    EntityManager em;
    
    @Resource
    UserTransaction ut;

    private FHWSUser user;

    public String edit(long id) {
        this.user = em.find(FHWSUser.class, id);
        return "edit-user?faces-redirect=true";
    }

    public String newUser() {
        this.user = new FHWSUser();
        return "edit-user?faces-redirect=true";
    }

    public String save() {
        try {
            System.out.println(user.getEmail());
            System.out.println(user.getId());
            
            ut.begin();
            if (user.getId() == null)
                em.persist(user);
            else
                em.merge(user);
            ut.commit();
            return "user-list?faces-redirect=true";
            
        } catch (NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException ex) {
            throw new RuntimeException(ex);
        }
    }

    public FHWSUser getUser() {
        return user;
    }

}

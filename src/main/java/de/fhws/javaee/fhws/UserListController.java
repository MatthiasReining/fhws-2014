/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Matthias Reining
 */
@ManagedBean
public class UserListController {
    
    @PersistenceContext
    EntityManager em;
        
    public List<User> getUserList() {
                
        return em.createNamedQuery(User.FIND_ALL, User.class).getResultList();
        
    }
    
    
}

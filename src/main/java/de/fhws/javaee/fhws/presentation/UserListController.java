/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.presentation;

import de.fhws.javaee.fhws.business.usermanagement.entity.FHWSUser;
import de.fhws.javaee.fhws.business.usermanagement.boundary.UserService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Matthias Reining
 */
@ManagedBean
public class UserListController {

    @EJB
    UserService userService;

    public List<FHWSUser> getUserList() {
        return userService.getAllFHWSUsers();
    }

}

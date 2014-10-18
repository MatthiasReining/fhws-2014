/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Matthias Reining
 */
@ManagedBean
public class UserListController {
        
    public List<User> getUserList() {
        return Database.getInstance().getAllUsers();
    }
    
    
}

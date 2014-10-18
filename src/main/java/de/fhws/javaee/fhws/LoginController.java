/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LoginController {
    
    User user = new User("max.mustermann@fhws.de", "adsfsadf", "adsf");
    

    public User getUser() {
        return user;
    }
    
    public String login() {
        System.out.println("email: " + user.getEmail());
        
        if (user.getEmail().equals(user.getPassword()))  {
            return "welcome.xhtml?faces-redirect=true";
        } else {
            return "login.xhtml";
            
        }
    }
    
    
    
}

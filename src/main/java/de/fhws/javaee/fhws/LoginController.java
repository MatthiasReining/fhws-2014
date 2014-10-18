/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import com.sun.jndi.toolkit.dir.SearchFilter;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {
    
    User user = new User();
    
    public LoginController()  {
        System.out.println("im LoginController Constructor");
    }
    

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

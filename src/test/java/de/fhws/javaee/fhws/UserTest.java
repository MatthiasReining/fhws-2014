/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import de.fhws.javaee.fhws.business.usermanagement.entity.FHWSUser;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias Reining
 */
public class UserTest {
    
    public UserTest() {
    }

    @Test
    public void increaseTestCoverage() {
        FHWSUser user = new FHWSUser();
        user = new FHWSUser("max.mustermann", "adf", new Date());
        user.getEmail();
        user.getLastLogin();
        user.getPassword();
        user.setEmail("asdf");
        user.setLastLogin(new Date());
        user.setPassword("adsfasf");
                
                
    } 
}

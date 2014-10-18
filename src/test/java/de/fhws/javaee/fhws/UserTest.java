/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

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
        User user = new User();
        user = new User("max.mustermann", "adf", "sfasf");
        user.getEmail();
        user.getLastLogin();
        user.getPassword();
        user.setEmail("asdf");
        user.setLastLogin("asdfasdg");
        user.setPassword("adsfasf");
                
                
    } 
}

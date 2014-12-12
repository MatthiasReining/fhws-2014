/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.business.usermanagement.boundary;

import de.fhws.javaee.fhws.business.usermanagement.controller.PWService;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Matthias Reining
 */
public class UserServiceTest {
    UserService UserService;
    
    @Before
    public void init() {
        UserService.pwService = new PWService();
    }
    @Test
    public void shouldWork() {
        
    }
    
}

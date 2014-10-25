/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import de.fhws.javaee.fhws.business.usermanagement.controller.PWService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias Reining
 */
public class PWServiceTest {

    @Test
    public void shouldCreateHash() {
        String hash = new PWService().createPWHash("geheim");
        System.out.println(hash);
        assertNotNull(hash);
    }

    @Test
    public void shouldCheckPWSuccessful() {
        String password = "geheim";
        String hash = new PWService().createPWHash(password);

        assertTrue(new PWService().checkPW(password, hash));

    }

    @Test
    public void shouldCheckPWNotSuccessful() {
        String password = "geheim";
        String hash = new PWService().createPWHash(password);

        assertFalse(new PWService().checkPW("falsch", hash));

    }

}

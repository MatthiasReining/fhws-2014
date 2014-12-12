/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.showcase.servlets;

import de.fhws.javaee.fhws.showcase.servlets.TestServlet;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Matthias Reining
 */
public class TestServletTest {

    TestServlet testServlet;

    @Before
    public void init() {
        testServlet = new TestServlet();
        
    }

    @Test
    public void shouldWork() {
        assertEquals(2000, testServlet.calculate(2000));
    }

    @Test
    public void shouldWork2() {
        assertEquals(1000, testServlet.calculate(1000));
    }
}

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

    }

    @Test
    public void shouldWork() {
        testServlet = new TestServlet();
        assertEquals(1000, testServlet.calculate(1000));
    }

}

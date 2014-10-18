/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import de.fhws.javaee.fhws.User;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReflectionTest {

    public ReflectionTest() {
    }

    @Test
    public void createUser() {
        User user = new User();

        assertNotNull(user);
    }
    
    @Test
    public void dummerTest() {
        User user = new User();
        assertEquals("max.mustermann@irgendwo.de", user.getEmail());
    }
    
    @Test
    public void dummerTest2() {
        User user = new User();
        if (user.getEmail().equals("max"))
            System.out.println("max");
    }

    @Test
    public void createUserByReflection() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Class clazz = Class.forName("de.fhws.javaee.fhws.User");
        Object obj = clazz.newInstance();
        User user = (User) obj;

        assertNotNull(user);
    }

    @Test(expected = ClassNotFoundException.class)
    public void shouldThrowClassNotFoundExceptoin() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {

        Class clazz = Class.forName("de.fhws.javaee.fhws.conroller.User2");
        Object obj = clazz.newInstance();
        Method m = clazz.getMethod("getName", (Class[]) null);
        Object o = m.invoke(obj, null);
        
    }
}

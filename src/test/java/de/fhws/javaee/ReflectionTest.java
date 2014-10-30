/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee;

import de.fhws.javaee.fhws.business.usermanagement.entity.FHWSUser;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReflectionTest {

    public ReflectionTest() {
    }

    @Test
    public void createUser() {
        FHWSUser user = new FHWSUser();

        assertNotNull(user);
    }

    @Test
    public void createUserByReflection() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Class clazz = Class.forName("de.fhws.javaee.fhws.business.usermanagement.entity.FHWSUser");
        Object obj = clazz.newInstance();
        FHWSUser user = (FHWSUser) obj;

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

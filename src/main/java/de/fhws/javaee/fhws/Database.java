/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Matthias Reining
 */
public class Database {
    private final static Database instance = new Database();
    private long idCounter = 100;
    
    private final Map<Long, User> users = new HashMap<>();
    
    private Database() {
        
        persistNewUser( new User("max.mustermann@fhws.de", "max", "today"));
        persistNewUser( new User("donald.duck@fhws.de", "donald", "today"));
        persistNewUser( new User("mickey.mouse@fhws.de", "mickey", "today"));
        
        
    }
    
    public static Database getInstance() {
        return instance;
    }   
    
    public User updateUser(User user) {
         if (user.getId() == null || user.getId() == 0)
            throw new RuntimeException("For update an Id has to be present!");
         users.put(user.getId(), user);
         return user;
    }
    
    public User getUserByEmail(String email) {
        if (email == null) return null;
        for (User user: users.values()) {
            if (email.equals(user.getEmail()))
                return user;
        }
        return null;
    }
    
    public User getUserById(long id) {
        return users.get(id);
    }
    
    public User persistNewUser(User user) {
        idCounter++;
        user.setId(idCounter);
        
        users.put(user.getId(), user);
        return user;
    }
    
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        for (User user: users.values()) {
            result.add(user);
        }
        return result;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.business.usermanagement.entity;

/**
 *
 * @author Matthias Reining
 */
public class FHWSLoginEvent {

    private FHWSUser user;

    public FHWSLoginEvent(FHWSUser user) {
        this.user = user;
    }

    public FHWSLoginEvent() {
    }

    public FHWSUser getUser() {
        return user;
    }

    public void setUser(FHWSUser user) {
        this.user = user;
    }

}

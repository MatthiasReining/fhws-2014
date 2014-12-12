/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.showcase.cdi;

import javax.enterprise.inject.Produces;

/**
 *
 * @author Matthias Reining
 */
public class Factory {

    @Produces
    @Special
    public Message fabrik() {

        Message message = new Message();
        message.setText("Gebaut in der Fabrik");
        //sm.text = "Gebaut in der Fabrik";

        return message;
    }

}

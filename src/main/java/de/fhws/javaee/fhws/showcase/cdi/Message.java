/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.showcase.cdi;

import java.io.Serializable;

public class Message implements Serializable {

    String text = "default";
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

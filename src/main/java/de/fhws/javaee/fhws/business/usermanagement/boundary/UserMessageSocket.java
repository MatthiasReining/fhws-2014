/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.javaee.fhws.business.usermanagement.boundary;

import de.fhws.javaee.fhws.business.usermanagement.entity.FHWSLoginEvent;
import de.fhws.javaee.fhws.business.usermanagement.entity.FHWSUser;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@Singleton
@ServerEndpoint("/websocket")
public class UserMessageSocket {

    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    public void broadcastNewLoggedInUser(@Observes FHWSLoginEvent loginEvent) throws IOException {
        FHWSUser fhws = loginEvent.getUser();
        String message = fhws.getEmail() + " ist neu angemeldet...";

        System.out.println(message);

        for (Session client : clients) {
            client.getBasicRemote().sendText(message);
        }

    }

    @OnMessage
    public void onMessage(String message, Session session)
            throws IOException {

        System.out.println("WebSocket message: " + message);

        synchronized (clients) {
            // Iterate over the connected sessions
            // and broadcast the received message
            for (Session client : clients) {
                if (!client.equals(session)) {
                    client.getBasicRemote().sendText(message);
                }
            }
        }

    }

    @OnOpen
    public void onOpen(Session session) {
        // Add session to the connected sessions set
        clients.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        // Remove session from the connected sessions set
        System.out.println("session beendet " + session.getId());
        clients.remove(session);
    }

}

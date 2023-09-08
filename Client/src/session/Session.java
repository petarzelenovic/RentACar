/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session;

import domain.User;
import forms.MainForm;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petar
 */
public class Session {

    private static Session instance;
    private Socket socket;
    private User ulogovani;
    
    private Session() {
        try {
            socket = new Socket("localhost", 9000);
        } catch (IOException ex) {

            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public User getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(User ulogovani) {
        this.ulogovani = ulogovani;
    }
    
    
    
    
    
    

}

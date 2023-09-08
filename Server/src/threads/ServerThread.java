/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petar
 */
public class ServerThread extends Thread {

    private ServerSocket serverSocket;
    private ArrayList<HandleClientThread> clients;

    public ServerThread() {
        try {
            serverSocket = new ServerSocket(9000);
            clients = new ArrayList<>();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                System.out.println("Cekanje korisnika...");
                Socket socket = serverSocket.accept();
                HandleClientThread thread = new HandleClientThread(socket);
                clients.add(thread);
                thread.start();
                System.out.println("Klijent povezan...");
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("INTERUPTOVAN");
        stopAllThreads();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void stopAllThreads() {
        for (HandleClientThread client : clients) {
            try {
                client.getSocket().close();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

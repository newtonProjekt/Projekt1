/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Client class
 * <p>
 * Handles connection to client socket.
 */

public class Client implements Runnable {

    private Socket connection;
    private PrintWriter out;
    private boolean disconnect = false;
    private CommandHandler commandHandler;

    public Client(Socket connection) {
        this.connection = connection;
        commandHandler = new CommandHandler(this);
        try {
            out = new PrintWriter(connection.getOutputStream(), true);
        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Returns client ipadress as String.
     *
     * @return String
     */
    public String getIP() {
        return String.valueOf(connection.getInetAddress());
    }

    /**
     * Sends string to client socket.
     *
     * @param jsonSend String formatted as JSON
     */
    public void send(String jsonSend) {
        out.println(jsonSend);
    }

    /**
     * Disconnects socket. And sets thread boolean to stop execution.
     */
    public void disconnect() {
        disconnect = true;
        try {
            connection.close();
        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Reads the JSON string from socket using Scanner and sends it to CommandHandler
     * for further processing.
     */
    @Override
    public void run() {
        String logLine = null;
        while (!disconnect) {

            try {
                Scanner sc = new Scanner(connection.getInputStream());

                while (sc.hasNextLine()) {
                    logLine = sc.nextLine();
                    commandHandler.parse(logLine);
                }
            } catch (IOException e) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }
}

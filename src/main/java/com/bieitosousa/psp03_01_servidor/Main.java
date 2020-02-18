/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.psp03_01_servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bieito
 */



public class Main {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    int indexUP = 100;
    int indexDown = 0;
    int n = (int) (Math.random() * indexUP + indexDown);

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                if (".".equals(inputLine)) {
//                    out.println("good bye");
//                    break;
//                }
//                out.println(inputLine);
//            }
        } catch (IOException e) {

        }

    }

    public boolean stringProgram() {
        String inputLine;
        try {
            while ((inputLine = in.readLine()) != null) {
                System.out.println("[Server : recibe]" + inputLine);
                if (inputLine.indexOf("CLI") != -1) {
                    if (inputLine.indexOf("hola 5") != -1) {
                        out.println("si");
                        System.out.println("[Server] escribe si");
                        return false;
                    }
                    out.println("no");
                    System.out.println("[Server] escribe no");
                    return true;
                } else {
                    System.out.println("[Server] -----");
                }
                out.println(inputLine);

            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean intProgram() {

        int inputLine;

        try {

            while ((inputLine = Integer.parseInt(in.readLine())) >= 0) {
                System.out.println("[Server : buscando el numero ]{" + n + "}");
                System.out.println("[Server : recive]" + inputLine);
                if (inputLine >= 0) {
                    if (inputLine == n) {
                        out.println("si");
                        System.out.println("[Server] escribe si");
                        return false;
                    } else {

                        String o = "no ";
                        if (inputLine < n) {
                            out.println(o + "menor");
                            System.out.println("[Server] escribe menor");
                            return true;
                        } else if (inputLine > n) {
                            out.println(o + "mayor");
                            System.out.println("[Server] escribe mayor");
                            return true;
                        } else {
                            out.println("no");
                            return true;
                        }

                    }
                } else {
                    System.out.println("[Server] -----");
                }
                out.println(inputLine);
            }

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {

        }

    }

    public static void main(String[] args) {
    pInt();   
    } 
        
    

   

    public static void pInt() {
        Main server = new Main();
        server.start(2000);
        int cont = 0;
        while (server.intProgram()) {
            System.out.println("se reiniciaconversacion " + cont++);

        };
    }
    
    
    

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tcpsocket;

/**
 *
 * @author Christian
 */
import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234); // Puerto en el que escucha el servidor
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Espera a que un cliente se conecte
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress().getHostName());

                try {
                    // Crea los flujos de entrada y salida para comunicarse con el cliente
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Mensaje recibido del cliente: " + inputLine);
                        out.println("Mensaje recibido: " + inputLine); // Envía la respuesta al cliente
                    }

                    // Cierra los flujos y la conexión con el cliente
                    out.close();
                    in.close();
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error al comunicarse con el cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al crear el socket del servidor: " + e.getMessage());
        }
    }
}

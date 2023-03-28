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

public class ClienteTCP {

    public static void main(String[] args) {
        String hostname = "localhost"; // Cambiar por el nombre o dirección IP del servidor si está en otro equipo
        int port = 1234; // Puerto en el que escucha el servidor

        try {
            // Crea la conexión con el servidor
            Socket clientSocket = new Socket(hostname, port);
            System.out.println("Conectado al servidor " + hostname + ":" + port);

            try {
                // Crea los flujos de entrada y salida para comunicarse con el servidor
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

                String inputLine;
                while ((inputLine = stdIn.readLine()) != null) {
                    out.println(inputLine); // Envía el mensaje al servidor

                    // Espera la respuesta del servidor y la muestra por pantalla
                    String response = in.readLine();
                    System.out.println("Respuesta del servidor: " + response);
                }

                // Cierra los flujos y la conexión con el servidor
                out.close();
                in.close();
                stdIn.close();
            } catch (IOException e) {
                System.err.println("Error al comunicarse con el servidor: " + e.getMessage());
            }

            clientSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("Nombre de host desconocido: " + e.getMessage());
        } catch (ConnectException e) {
            System.err.println("No se puede conectar al servidor: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al crear el socket del cliente: "
                    + e.getMessage());
        }
    }
}

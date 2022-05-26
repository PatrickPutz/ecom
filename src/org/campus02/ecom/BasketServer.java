package org.campus02.ecom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BasketServer {

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(1111)){

            System.out.println("server started on port 1111.");
            while(true){
                System.out.println("waiting for client...");
                Socket client = serverSocket.accept();
                System.out.println("client connected.");

                EcommerceLogic ec = new EcommerceLogic(client);

                Thread thread = new Thread(ec);
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

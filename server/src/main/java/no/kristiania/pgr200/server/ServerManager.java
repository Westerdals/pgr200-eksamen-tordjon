package no.kristiania.pgr200.server;

import java.io.IOException;

public class ServerManager {
    public static void main(String[] args) {
        HttpServer server = new HttpServer(8080);
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

package no.kristiania.pgr200.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class HttpRequest {
    private String hostname;
    private String path;
    private int port;


    // FIXME: specialization in sub/superclass instead of this solution
    public HttpRequest(String hostname, int port, String uri) {
        this.hostname = hostname;
        this.path = uri;
        this.port = port;
    }

    public HttpResponse execute() throws IOException {
        try(Socket socket = new Socket(hostname, port)) {

            OutputStream output = socket.getOutputStream();
            // InputStream input = socket.getInputStream(); // FIXME : remove if not useful

            output.write(("GET " + path + " HTTP/1.1\r\n").getBytes());
            output.write(("Host: " + hostname + "\r\n").getBytes());
            output.write("Connection: close\r\n".getBytes());
            output.write("\r\n".getBytes());

            return new HttpResponse(socket);

        }
    }
}

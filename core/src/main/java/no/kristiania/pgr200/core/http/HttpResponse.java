package no.kristiania.pgr200.core.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class HttpResponse {

    private String body;
    private int status;
    private HttpResponseStreamReader reader;

    public HttpResponse(Socket socket) throws IOException {
        InputStream input = socket.getInputStream();

        reader = new HttpResponseStreamReader(input);
        body = reader.getBody();
        status = reader.getStatusCode();

        // done with server
        socket.close();
    }



    public int getStatusCode() {
        return status;
    }
    public String getHeader(String field) {
        return reader.getHeader(field);
    }
    public String getBody() {
        return body.trim();
    }
}
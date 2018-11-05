package no.kristiania.pgr200.client;

import no.kristiania.pgr200.core.http.HttpUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class HttpResponse {

    private String body;
    private int status;
    private HttpUtil reader;

    public HttpResponse(Socket socket) throws IOException {
        InputStream input = socket.getInputStream();

        reader = new HttpUtil(input);
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

    @Override
    public String toString() {
        return "HttpResponse{" +
                "body='" + body + '\'' +
                ", status=" + status +
                ", reader=" + reader +
                '}';
    }
}
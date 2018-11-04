package no.kristiania.pgr200.server;

import no.kristiania.pgr200.core.http.Status;

import java.util.HashMap;
import java.util.Map;

public class ServerResponse {

    private Status status = new Status(500);
    private String body;
    private String method; //TODO: skal ikke ha?
    Map<String, String> headers = new HashMap<>();

    public ServerResponse() {
        headers = new HashMap<>();
    }

    public void setStatus(int code) {
        this.status = new Status(code);
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Status getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}

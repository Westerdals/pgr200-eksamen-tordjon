package no.kristiania.pgr200.server.command.connecting;

import com.google.gson.Gson;
import no.kristiania.pgr200.server.ServerResponse;

public interface ServerCommand {

    /**
     * Sets the standard headers for this type of command
     */

    ServerResponse response = new ServerResponse();

    default <T> void assignStandardHttp(T content) {
        Gson gson = new Gson();

        response.setStatus(201);
        response.getHeaders().put("Content-Length", "0");
        response.setBody(gson.toJson(content));
    }

}



package no.kristiania.pgr200.server.command;

import com.google.gson.Gson;
import no.kristiania.pgr200.server.ServerResponse;

public interface ServerCommand {

    /**
     * Sets the standard headers for this type of command
     */

    ServerResponse response = new ServerResponse();

    /**
     * Assigns the standard http-data
     * for any command on the server.
     * They are expected to be overridden in
     * the few cases where they are not correct.
     * @param content will be set to body as JSON
     */
    default <T> void assignStandardHttp(T content) {
        Gson gson = new Gson();
        String json = gson.toJson(content);
        response.setStatus(200);
        response.getHeaders().put("Content-Length", json.length() + "");
        response.setBody(json);
    }

}



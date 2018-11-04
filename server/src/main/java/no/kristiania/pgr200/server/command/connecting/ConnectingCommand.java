package no.kristiania.pgr200.server.command.connecting;

import com.google.gson.Gson;
import no.kristiania.pgr200.server.command.Command;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class ConnectingCommand extends Command {
    @Override
    public <T> void assignStandardHttp(T content) {
        Gson gson = new Gson();

        response.setStatus(201);
        response.getHeaders().put("Content-Length", "0");
        response.setBody(gson.toJson(content));
    }
}

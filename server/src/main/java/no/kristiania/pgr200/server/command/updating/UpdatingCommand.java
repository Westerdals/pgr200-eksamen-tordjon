package no.kristiania.pgr200.server.command.updating;

import com.google.gson.Gson;
import no.kristiania.pgr200.server.command.Command;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class UpdatingCommand extends Command {
    @Override
    public <T> void assignStandardHttp(T content) {
        Gson gson = new Gson();
        String json = gson.toJson(content);

        response.setStatus(204);
        response.getHeaders().put("Content-Type", "application/json");
        response.getHeaders().put("Content-Length", json.length() + "");
        response.setBody(json);
    }
}

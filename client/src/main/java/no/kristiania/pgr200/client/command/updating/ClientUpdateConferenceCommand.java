package no.kristiania.pgr200.client.command.updating;


import com.google.gson.reflect.TypeToken;
import no.kristiania.pgr200.client.HttpRequest;
import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.client.command.ClientCommand;
import no.kristiania.pgr200.core.command.updating.UpdateConferenceCommand;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Conference;
import no.kristiania.pgr200.core.model.Talk;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;

public class ClientUpdateConferenceCommand extends UpdateConferenceCommand implements ClientCommand {
    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {

        parameters.put("name", name);
        parameters.put("id", id.toString());


        Uri uri = new Uri("/api/update/conference", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());

        HttpResponse response = req.execute();
        if(checkForError(response)) {
            return response;
        }


        System.out.println("Updated conference: ");
        Conference retrieved = gson.fromJson(response.getBody(), Conference.class);
        System.out.println(retrieved);
        return response;
    }
}

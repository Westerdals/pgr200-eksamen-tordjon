package no.kristiania.pgr200.client.command;

import no.kristiania.pgr200.client.HttpRequest;
import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.core.command.CreateDemoConferenceCommand;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Conference;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class ClientCreateDemoConferenceCommand extends CreateDemoConferenceCommand implements ClientCommand {
    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {

        Uri uri = new Uri("/api/insert/democonference", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());

        HttpResponse response = req.execute();
        if(checkForError(response)) {
            return response;
        }


        Conference conference = gson.fromJson(response.getBody(), Conference.class);

        System.out.println("Conference created:");
        System.out.println(conference);

        return response;
    }
}

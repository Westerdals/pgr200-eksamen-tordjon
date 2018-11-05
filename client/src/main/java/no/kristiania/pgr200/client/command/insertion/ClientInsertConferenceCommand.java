package no.kristiania.pgr200.client.command.insertion;

import no.kristiania.pgr200.client.HttpRequest;
import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.client.command.ClientCommand;
import no.kristiania.pgr200.core.command.insertion.InsertConferenceCommand;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Conference;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class ClientInsertConferenceCommand extends InsertConferenceCommand implements ClientCommand  {


    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {
        parameters.put("name", name);

        Uri uri = new Uri("/api/insert/conference", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());


        HttpResponse response = req.execute();
        if(checkForError(response)) {
            return response;
        }


        System.out.println("Inserted new conference: ");
        Conference retrieved = gson.fromJson(response.getBody(), Conference.class);
        System.out.println(retrieved);
        return response;

    }
}

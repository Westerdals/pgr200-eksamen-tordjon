package no.kristiania.pgr200.client.command.insertion;

import no.kristiania.pgr200.client.HttpRequest;
import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.client.command.ClientCommand;
import no.kristiania.pgr200.core.command.insertion.InsertTalkCommand;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Talk;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class ClientInsertTalkCommand extends InsertTalkCommand implements ClientCommand  {

    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {

        parameters.put("title", title);
        parameters.put("description", description);
        parameters.put("topic", topic);

        System.out.println(parameters);
        Uri uri = new Uri("/api/insert/talk", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());

        HttpResponse response = req.execute();
        if(checkForError(response)) {
            return response;
        }


        System.out.println("Inserted new talk: ");
        Talk retrieved = gson.fromJson(response.getBody(), Talk.class);
        System.out.println(retrieved);
        return response;
    }
}
package no.kristiania.pgr200.client.command.deletion;

import no.kristiania.pgr200.client.HttpRequest;
import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.client.command.ClientCommand;
import no.kristiania.pgr200.core.command.deletion.DeleteTalkCommand;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Talk;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class ClientDeleteTalkCommand extends DeleteTalkCommand implements ClientCommand {


    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {
        parameters.put("id", id.toString());

        Uri uri = new Uri("/api/delete/talk", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());

        HttpResponse response = req.execute();
        if (checkForError(response)) {
            return response;
        }

        System.out.println("Deleted requested talk");
        Talk retrieved = gson.fromJson(response.getBody(), Talk.class);
        System.out.println(retrieved);
        return response;
    }

}

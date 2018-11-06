package no.kristiania.pgr200.client.command.deletion;

import no.kristiania.pgr200.core.http.HttpRequest;
import no.kristiania.pgr200.core.http.HttpResponse;
import no.kristiania.pgr200.client.command.ClientCommand;
import no.kristiania.pgr200.core.command.deletion.DeleteTimeslotCommand;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Timeslot;

import javax.sql.DataSource;
import java.io.IOException;

public class ClientDeleteTimeslotCommand extends DeleteTimeslotCommand implements ClientCommand {


    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {
        parameters.put("id", id.toString());

        Uri uri = new Uri("/api/delete/timeslot", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());

        HttpResponse response = req.execute();
        if (checkForError(response)) {
            return response;
        }

        System.out.println("Deleted requested timeslot");
        Timeslot retrieved = gson.fromJson(response.getBody(), Timeslot.class);
        System.out.println(retrieved);
        return response;
    }
}

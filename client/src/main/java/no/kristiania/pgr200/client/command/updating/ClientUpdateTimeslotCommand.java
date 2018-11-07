package no.kristiania.pgr200.client.command.updating;

import no.kristiania.pgr200.client.command.ClientCommand;
import no.kristiania.pgr200.core.command.updating.UpdateTimeslotCommand;
import no.kristiania.pgr200.core.http.HttpRequest;
import no.kristiania.pgr200.core.http.HttpResponse;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Timeslot;

import javax.sql.DataSource;
import java.io.IOException;

public class ClientUpdateTimeslotCommand extends UpdateTimeslotCommand implements ClientCommand {

    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {

        if (start != null)
            parameters.put("start", start.toString());
        if (end != null)
            parameters.put("end", end.toString());
        if (id != null)
            parameters.put("id", id.toString());


        Uri uri = new Uri("/api/update/timeslot", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());

        HttpResponse response = req.execute();
        if(checkForError(response)) {
            return response;
        }


        System.out.println("Updated conference: ");
        Timeslot retrieved = gson.fromJson(response.getBody(), Timeslot.class);
        System.out.println(retrieved);
        return response;
    }
}

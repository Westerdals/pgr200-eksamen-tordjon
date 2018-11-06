package no.kristiania.pgr200.client.command.updating;

import no.kristiania.pgr200.core.http.HttpRequest;
import no.kristiania.pgr200.core.http.HttpResponse;
import no.kristiania.pgr200.client.command.ClientCommand;
import no.kristiania.pgr200.core.command.updating.UpdateDayCommand;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Day;

import javax.sql.DataSource;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ClientUpdateDayCommand extends UpdateDayCommand implements ClientCommand {
    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {

        parameters.put("date", date.format(DateTimeFormatter.ofPattern("d.MM.yyyy")));
        parameters.put("id", id.toString());



        Uri uri = new Uri("/api/update/day", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());

        HttpResponse response = req.execute();
        if(checkForError(response)) {
            return response;
        }


        System.out.println("Updated day: ");
        Day retrieved = gson.fromJson(response.getBody(), Day.class);
        System.out.println(retrieved);
        return response;
    }
}

package no.kristiania.pgr200.client.command.insertion;

import no.kristiania.pgr200.client.command.ClientCommand;
import no.kristiania.pgr200.core.command.insertion.InsertDayCommand;
import no.kristiania.pgr200.core.http.HttpRequest;
import no.kristiania.pgr200.core.http.HttpResponse;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Day;

import javax.sql.DataSource;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ClientInsertDayCommand extends InsertDayCommand implements ClientCommand {

    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {


        if(date != null)
            parameters.put("date", date.format(DateTimeFormatter.ofPattern("d.MM.yyyy")));

        Uri uri = new Uri("/api/insert/day", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());


        HttpResponse response = req.execute();
        if(checkForError(response)) {
            return response;
        }


        System.out.println("Inserted new day: ");
        Day retrieved = gson.fromJson(response.getBody(), Day.class);
        System.out.println(retrieved);
        return response;

    }
}

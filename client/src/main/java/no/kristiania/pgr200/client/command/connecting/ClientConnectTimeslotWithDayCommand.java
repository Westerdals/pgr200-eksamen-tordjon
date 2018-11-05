package no.kristiania.pgr200.client.command.connecting;

import no.kristiania.pgr200.client.HttpRequest;
import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.client.command.ClientCommand;
import no.kristiania.pgr200.core.command.connecting.ConnectTimeslotWithDayCommand;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Timeslot;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class ClientConnectTimeslotWithDayCommand extends ConnectTimeslotWithDayCommand implements ClientCommand {

    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {
        parameters.put("timeslot", timeslotId.toString());
        parameters.put("day", dayId.toString());

        Uri uri = new Uri("/api/connect/timeslot-with-day", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());

        HttpResponse response = req.execute();
        if (checkForError(response)) {
            return response;
        }

        System.out.println("Connected specified timeslot and day");


        return response;
    }

}

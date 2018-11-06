package no.kristiania.pgr200.client.command.listing;

import com.google.gson.reflect.TypeToken;
import no.kristiania.pgr200.core.http.HttpRequest;
import no.kristiania.pgr200.core.http.HttpResponse;
import no.kristiania.pgr200.client.command.ClientCommand;
import no.kristiania.pgr200.core.command.listing.ListTimeslotsCommand;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Timeslot;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public class ClientListTimeslotsCommand extends ListTimeslotsCommand implements ClientCommand {

    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {

        Uri uri = new Uri("/api/list/timeslots", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());

        HttpResponse response = req.execute();
        if(checkForError(response)) {
            return response;
        }


        System.out.println("Talks: ");
        Type collectionType = new TypeToken<Collection<Timeslot>>(){}.getType();
        Collection<Timeslot> timeslots = gson.fromJson(response.getBody(), collectionType);
        for(Timeslot t : timeslots){
            System.out.println(t);
        }

        return response;
    }
}

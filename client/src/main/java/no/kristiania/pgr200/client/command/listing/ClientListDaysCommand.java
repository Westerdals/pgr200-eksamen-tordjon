package no.kristiania.pgr200.client.command.listing;


import com.google.gson.reflect.TypeToken;
import no.kristiania.pgr200.core.http.HttpRequest;
import no.kristiania.pgr200.core.http.HttpResponse;
import no.kristiania.pgr200.client.command.ClientCommand;
import no.kristiania.pgr200.core.command.listing.ListDaysCommand;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Day;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public class ClientListDaysCommand extends ListDaysCommand implements ClientCommand {


    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {

        Uri uri = new Uri("/api/list/days", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());

        HttpResponse response = req.execute();
        if(checkForError(response)) {
            return response;
        }


        System.out.println("Days: ");
        Type collectionType = new TypeToken<Collection<Day>>(){}.getType();
        Collection<Day> days = gson.fromJson(response.getBody(), collectionType);
        for(Day d : days){
            System.out.println(d);
        }

        return response;
    }
}

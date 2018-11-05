package no.kristiania.pgr200.client.command.listing;

import com.google.gson.reflect.TypeToken;
import no.kristiania.pgr200.client.HttpRequest;
import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.client.command.ClientCommand;
import no.kristiania.pgr200.core.command.listing.ListConferencesCommand;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Conference;
import no.kristiania.pgr200.core.model.Talk;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;

public class ClientListConferencesCommand extends ListConferencesCommand implements ClientCommand {

    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {

        Uri uri = new Uri("/api/list/conferences", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());

        HttpResponse response = req.execute();
        if(checkForError(response)) {
            return response;
        }


        System.out.println("Conferences: ");
        Type collectionType = new TypeToken<Collection<Conference>>(){}.getType();
        Collection<Conference> conferences = gson.fromJson(response.getBody(), collectionType);
        for(Conference c : conferences){
            System.out.println(c);
        }

        return response;
    }
}

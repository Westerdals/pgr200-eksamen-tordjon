package no.kristiania.pgr200.client.command.listing;


import com.google.gson.reflect.TypeToken;
import no.kristiania.pgr200.client.HttpRequest;
import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.client.command.ClientCommand;
import no.kristiania.pgr200.core.command.listing.ListTalksCommand;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Talk;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public class ClientListTalksCommand extends ListTalksCommand implements ClientCommand {


    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {

        Uri uri = new Uri("/api/list/talks", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());

        HttpResponse response = req.execute();
        if(checkForError(response)) {
            return response;
        }


        System.out.println("Talks: ");
        Type collectionType = new TypeToken<Collection<Talk>>(){}.getType();
        Collection<Talk> talks = gson.fromJson(response.getBody(), collectionType);
        for(Talk t : talks){
            System.out.println(t);
        }

        return response;
    }



}

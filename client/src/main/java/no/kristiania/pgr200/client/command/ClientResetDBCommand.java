package no.kristiania.pgr200.client.command;

import no.kristiania.pgr200.core.http.HttpRequest;
import no.kristiania.pgr200.core.http.HttpResponse;
import no.kristiania.pgr200.core.command.ResetDBCommand;
import no.kristiania.pgr200.core.http.uri.Uri;

import javax.sql.DataSource;
import java.io.IOException;

public class ClientResetDBCommand extends ResetDBCommand implements ClientCommand{

    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {
        Uri uri = new Uri("/api/resetdb", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());

        HttpResponse response = req.execute();
        System.out.println("hei tord");

        if(checkForError(response)) {
            return response;
        }

        System.out.println("Database is reset");

        return response;
    }

}

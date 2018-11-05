package no.kristiania.pgr200.client.command.insertion;

import no.kristiania.pgr200.client.HttpRequest;
import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.core.command.insertion.InsertTalkCommand;
import no.kristiania.pgr200.core.http.uri.Uri;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ClientInsertTalkCommand extends InsertTalkCommand {

    @Override
    public HttpResponse execute(DataSource dataSource) throws SQLException {
        System.out.println("insert talk");

        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("title", title);
        parameters.put("description", description);
        parameters.put("topic", topic);


        Uri uri = new Uri("/api/insert/talk", parameters); //Map<String,String>



        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());
        try {
            HttpResponse response = req.execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //throw new NotImplementedException();
        return null;
    }
}
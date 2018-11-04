package no.kristiania.pgr200.client.command.insertion;

import no.kristiania.pgr200.client.HttpRequest;
import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.core.command.insertion.InsertTalkCommand;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class ClientInsertTalkCommand extends InsertTalkCommand {

    @Override
    public HttpResponse execute(DataSource dataSource) throws SQLException {
        System.out.println("insert talk");
        HttpRequest req = new HttpRequest("localhost", 8080,
                "/api/insert/talk?title=" + title
                        + "&description=" + description
                        + "&topic=" + topic);
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
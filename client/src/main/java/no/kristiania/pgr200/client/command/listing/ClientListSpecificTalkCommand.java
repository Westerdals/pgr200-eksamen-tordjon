package no.kristiania.pgr200.client.command.listing;

import no.kristiania.pgr200.core.command.listing.ListSpecificTalkCommand;
import no.kristiania.pgr200.core.http.HttpResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ClientListSpecificTalkCommand extends ListSpecificTalkCommand  {

    @Override
    public HttpResponse execute(DataSource dataSource) throws SQLException {
        throw new NotImplementedException();
    }
}

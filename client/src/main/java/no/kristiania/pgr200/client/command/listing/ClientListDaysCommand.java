package no.kristiania.pgr200.client.command.listing;


import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.core.command.listing.ListDaysCommand;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ClientListDaysCommand extends ListDaysCommand  {

    @Override
    public HttpResponse execute(DataSource dataSource) throws SQLException {
        throw new NotImplementedException();
    }
}

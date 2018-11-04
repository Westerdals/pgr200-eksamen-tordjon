package no.kristiania.pgr200.client.command.insertion;

import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.core.command.insertion.InsertTimeslotCommand;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ClientInsertTimeslotCommand extends InsertTimeslotCommand {

    @Override
    public HttpResponse execute(DataSource dataSource) throws SQLException {
        throw new NotImplementedException();
    }
}

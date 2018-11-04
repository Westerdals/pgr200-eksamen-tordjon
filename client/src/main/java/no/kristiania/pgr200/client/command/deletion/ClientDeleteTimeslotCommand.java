package no.kristiania.pgr200.client.command.deletion;

import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.core.command.deletion.DeleteTimeslotCommand;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ClientDeleteTimeslotCommand extends DeleteTimeslotCommand {


    @Override
    public HttpResponse execute(DataSource dataSource) throws SQLException {
        throw new NotImplementedException();
    }
}

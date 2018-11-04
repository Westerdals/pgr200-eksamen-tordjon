package no.kristiania.pgr200.client.command.updating;

import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.core.command.updating.UpdateTimeslotCommand;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ClientUpdateTimeslotCommand extends UpdateTimeslotCommand {

    @Override
    public HttpResponse execute(DataSource dataSource) throws SQLException {
        throw new NotImplementedException();
    }
}

package no.kristiania.pgr200.server.command.connecting;

import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.core.command.connecting.ConnectDayWithConference;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ClientConnectDayWithConference extends ConnectDayWithConference {

    @Override
    public HttpResponse execute(DataSource dataSource) throws SQLException {
        throw new NotImplementedException();
    }
}

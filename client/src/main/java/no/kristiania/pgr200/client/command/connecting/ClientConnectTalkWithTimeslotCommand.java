package no.kristiania.pgr200.client.command.connecting;

import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.core.command.connecting.ConnectTalkWithTimeslotCommand;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ClientConnectTalkWithTimeslotCommand extends ConnectTalkWithTimeslotCommand {

    @Override
    public HttpResponse execute(DataSource dataSource) throws SQLException {
        throw new NotImplementedException();
    }
}

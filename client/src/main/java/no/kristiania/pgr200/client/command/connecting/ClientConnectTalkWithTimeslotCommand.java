package no.kristiania.pgr200.client.command.connecting;

import no.kristiania.pgr200.core.command.connecting.ConnectTalkWithTimeslotCommand;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.SQLException;

public class  ServerConnectTalkWithTimeslotCommand extends ConnectTalkWithTimeslotCommand {


    @Override
    public <T> T execute(DataSource dataSource) throws SQLException {
        throw new NotImplementedException();
    }
}

package no.kristiania.pgr200.server.command.connecting;

import no.kristiania.pgr200.core.command.connecting.ConnectTalkWithTimeslotCommand;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.TimeslotDao;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.UUID;

public class  ServerConnectTalkWithTimeslotCommand extends ConnectTalkWithTimeslotCommand implements ServerCommand {


    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        TimeslotDao dao = new TimeslotDao(dataSource);
        //TODO: FIXME: 
        return response;
    }
}

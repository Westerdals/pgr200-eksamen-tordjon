package no.kristiania.pgr200.client.command.deletion;

import no.kristiania.pgr200.core.command.deletion.DeleteTimeslotCommand;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.TimeslotDao;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ServerDeleteTimeslotCommand extends DeleteTimeslotCommand implements ServerCommand {



    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        TimeslotDao dao = new TimeslotDao(dataSource);
        dao.delete(id);
        assignStandardHttp(id);
        return response;
    }
}

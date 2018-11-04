package no.kristiania.pgr200.server.command.connecting;

import no.kristiania.pgr200.core.command.connecting.ConnectDayWithConference;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.DayDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class ServerConnectDayWithConference extends ConnectDayWithConference {



    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        ServerResponse response = new ServerResponse();

        DayDao dao = new DayDao(dataSource);
        dao.connectDayToConference(conferenceId, dayId);

        assignStandardHttp("");  // no content is sent back

        return response;
    }
}

package no.kristiania.pgr200.server.command.connecting;

import no.kristiania.pgr200.core.command.connecting.ConnectTimeslotWithDayCommand;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.TimeslotDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class ServerConnectTimeslotWithDayCommand extends ConnectTimeslotWithDayCommand implements ServerCommand {

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {

        if (timeslotId == null || dayId == null) {
            System.out.println("both \"-timeslot\" and \"-day\" is required.");
            return null;
        }

        TimeslotDao dao = new TimeslotDao(dataSource);
        dao.connectTimeslotToDay(timeslotId, dayId);

        assignStandardHttp("");

        return response;
    }

}

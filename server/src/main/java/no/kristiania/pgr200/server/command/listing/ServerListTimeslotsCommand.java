package no.kristiania.pgr200.server.command.listing;

import no.kristiania.pgr200.core.command.listing.ListTimeslotsCommand;
import no.kristiania.pgr200.core.model.Timeslot;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.Dao;
import no.kristiania.pgr200.server.database.dao.TimeslotDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class ServerListTimeslotsCommand extends ListTimeslotsCommand implements ServerCommand {

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        Dao<Timeslot> dao = new TimeslotDao(dataSource);

        List<Timeslot> timeslots = dao.retrieveAll();
        assignStandardHttp(timeslots);

        return response;
    }
}

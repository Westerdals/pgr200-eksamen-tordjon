package no.kristiania.pgr200.server.command.listing;


import no.kristiania.pgr200.core.command.listing.ListDaysCommand;
import no.kristiania.pgr200.core.model.Day;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.Dao;
import no.kristiania.pgr200.server.database.dao.DayDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class ServerListDaysCommand extends ListDaysCommand implements ServerCommand {

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        Dao<Day> dao = new DayDao(dataSource);
        List<Day> days = dao.retrieveAll();

        assignStandardHttp(days);

        return response;
    }
}

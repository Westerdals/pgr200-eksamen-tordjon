package no.kristiania.pgr200.server.command.listing;


import model.Day;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.Dao;
import no.kristiania.pgr200.server.database.dao.DayDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ListDaysCommand extends ListCommand {

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        return new ListDaysCommand();
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        Dao<Day> dao = new DayDao(dataSource);
        List<Day> days = dao.retrieveAll();

        assignStandardHttp(days);

        return response;
    }
}

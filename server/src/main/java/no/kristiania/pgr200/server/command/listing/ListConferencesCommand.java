package no.kristiania.pgr200.server.command.listing;

import no.kristiania.pgr200.core.model.Conference;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.Dao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ListConferencesCommand extends ListCommand {

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        return new ListConferencesCommand();
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        Dao<Conference> dao = new ConferenceDao(dataSource);
        List<Conference> conferences = dao.retrieveAll();

        assignStandardHttp(conferences);
        return response;
    }
}

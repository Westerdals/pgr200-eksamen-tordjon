package no.kristiania.pgr200.client.command.listing;

import no.kristiania.pgr200.core.command.listing.ListConferencesCommand;
import no.kristiania.pgr200.core.model.Conference;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.Dao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class ServerListConferencesCommand extends ListConferencesCommand implements ServerCommand {


    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        Dao<Conference> dao = new ConferenceDao(dataSource);
        List<Conference> conferences = dao.retrieveAll();

        assignStandardHttp(conferences);
        return response;
    }
}

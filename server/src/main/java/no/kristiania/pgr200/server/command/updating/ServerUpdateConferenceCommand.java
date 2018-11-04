package no.kristiania.pgr200.server.command.updating;


import no.kristiania.pgr200.core.command.updating.UpdateConferenceCommand;
import no.kristiania.pgr200.core.model.Conference;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.Dao;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ServerUpdateConferenceCommand extends UpdateConferenceCommand implements ServerCommand {

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {

        if (id == null) {
            System.out.println("\"-id\" required.");
            return null;
        }

        Dao<Conference> dao = new ConferenceDao(dataSource);
        Conference conference = new Conference(id, name);
        dao.update(conference);

        // get all data, not just the updated one //TODO: Kan bli bedre om vi wrapper JSON -Tord (samme potensial i de andre update)
        Conference updated = dao.retrieve(conference.getId());

        assignStandardHttp(updated);

        return response;
    }
}

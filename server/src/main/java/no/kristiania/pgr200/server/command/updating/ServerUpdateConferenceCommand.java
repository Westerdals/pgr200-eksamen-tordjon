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
            //System.out.println("\"-id\" required.");
            assignStandardHttp("You cannot update a conference that does not exist.");
            response.setStatus(400);
            return response;
        }

        Dao<Conference> dao = new ConferenceDao(dataSource);
        Conference conference = new Conference(id, name);
        dao.update(conference);

        Conference updated = dao.retrieve(conference.getId());

        assignStandardHttp(updated);

        return response;
    }
}

package no.kristiania.pgr200.client.command.deletion;

import no.kristiania.pgr200.core.command.deletion.DeleteConferenceCommand;
import no.kristiania.pgr200.core.model.Conference;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.Dao;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ServerDeleteConferenceCommand extends DeleteConferenceCommand implements ServerCommand {

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        Dao<Conference> dao = new ConferenceDao(dataSource);
        dao.delete(id);
        assignStandardHttp(id);
        return response;

    }

}

package no.kristiania.pgr200.server.command;


import no.kristiania.pgr200.core.command.ShowScheduleCommand;
import no.kristiania.pgr200.core.model.Conference;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.Dao;

import javax.sql.DataSource;
import java.sql.SQLException;


public class ServerShowScheduleCommand extends ShowScheduleCommand implements ServerCommand {

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        Dao<Conference> dao = new ConferenceDao(dataSource);
        Conference conference = dao.retrieve(id);

        assignStandardHttp(conference);
        return response;
    }
}

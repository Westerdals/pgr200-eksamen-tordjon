package no.kristiania.pgr200.server.command.insertion;

import no.kristiania.pgr200.core.command.insertion.InsertConferenceCommand;
import no.kristiania.pgr200.core.model.Conference;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.Dao;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ServerInsertConferenceCommand extends InsertConferenceCommand implements ServerCommand {


    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        Dao<Conference> dao = new ConferenceDao(dataSource);
        Conference conference = new Conference(name);
        dao.insert(conference);


        assignStandardHttp(conference);
        return response;
    }
}

package no.kristiania.pgr200.client.command.listing;

import no.kristiania.pgr200.core.command.listing.ListSpecificTalkCommand;
import no.kristiania.pgr200.core.model.Talk;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.TalkDao;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ServerListSpecificTalkCommand extends ListSpecificTalkCommand implements ServerCommand {

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        TalkDao dao =  new TalkDao(dataSource);
        Talk talk = dao.retrieve(id);

        assignStandardHttp(talk);

        return response;
    }
}

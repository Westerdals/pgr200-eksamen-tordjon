package no.kristiania.pgr200.client.command.listing;


import no.kristiania.pgr200.core.command.listing.ListTalksCommand;
import no.kristiania.pgr200.core.model.Talk;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.Dao;
import no.kristiania.pgr200.server.database.dao.TalkDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class ServerListTalksCommand extends ListTalksCommand implements ServerCommand {
    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        Dao<Talk> dao = new TalkDao(dataSource);
        List<Talk> talks = dao.retrieveAll();

        assignStandardHttp(talks);

        return response;
    }


}

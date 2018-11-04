package no.kristiania.pgr200.server.command.listing;


import no.kristiania.pgr200.core.model.Talk;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.Dao;
import no.kristiania.pgr200.server.database.dao.TalkDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ListTalksCommand extends ListCommand {
    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        return new ListTalksCommand();
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        Dao<Talk> dao = new TalkDao(dataSource);
        List<Talk> talks = dao.retrieveAll();

        assignStandardHttp(talks);

        return response;
    }


}

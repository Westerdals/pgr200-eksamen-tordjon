package no.kristiania.pgr200.server.command.listing;


import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.Dao;
import no.kristiania.pgr200.server.database.dao.TalkDao;
import no.kristiania.pgr200.server.database.model.Talk;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ListTalksCommand extends Command {
    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        return new ListTalksCommand();
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {
        Dao<Talk> dao = new TalkDao(dataSource);

        List<Talk> talks = dao.retrieveAll();

        for(Talk talk : talks) {
            System.out.println(talk);
        }
    }
}

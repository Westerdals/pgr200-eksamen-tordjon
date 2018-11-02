package no.kristiania.pgr200.program.command.listing;

import no.kristiania.pgr200.database.dao.Dao;
import no.kristiania.pgr200.database.dao.TalkDao;
import no.kristiania.pgr200.database.model.Talk;
import no.kristiania.pgr200.program.command.Command;

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

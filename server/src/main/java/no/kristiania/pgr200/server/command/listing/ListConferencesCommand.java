package no.kristiania.pgr200.server.command.listing;

import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.Dao;
import no.kristiania.pgr200.server.database.model.Conference;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ListConferencesCommand extends Command {
    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        return new ListConferencesCommand();
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {
        Dao<Conference> dao = new ConferenceDao(dataSource);
        List<Conference> conferences = dao.retrieveAll();

        for(Conference conference : conferences){
            System.out.println(conference);
        }
    }
}

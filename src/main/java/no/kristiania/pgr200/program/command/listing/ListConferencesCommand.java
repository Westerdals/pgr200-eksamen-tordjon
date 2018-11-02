package no.kristiania.pgr200.program.command.listing;

import no.kristiania.pgr200.database.dao.ConferenceDao;
import no.kristiania.pgr200.database.dao.Dao;
import no.kristiania.pgr200.database.model.Conference;
import no.kristiania.pgr200.program.command.Command;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class ListConferencesCommand extends Command {
    @Override
    public Command build(String[] strings) throws IllegalArgumentException {
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
